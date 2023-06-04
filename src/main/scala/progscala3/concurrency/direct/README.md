# README for `direct`

> **Note:** A more complete version of this text can be found at [Dean's Scala 3 blog](https://medium.com/scala-3).

This directory was added in June 2023, well after _Programming Scala, Third Edition_ was published. The code in this section illustrates the new _direct style_ that was added to Scala starting in version 3.3.0.

I recommend viewing [this Martin Odersky talk](https://www.youtube.com/watch?v=0Fm0y4K4YO8) about the subject. The following discussion is based on it.

The idea of _direct style_ is to explore how we can create and use primitives, such as concurrency abstractions, that don't require the boilerplate of monads. For motivation, consider the example of `Future`s. You currently use them like this:

```scala
val sum =
  val f1 = Future(c1.read)
  val f2 = Future(c2.read)
  for
    x <- f1
    y <- f2
  yield x + y
```

The `for` comprehension invokes `map` and `flatMap` to wait on the futures and extract the values. 

A new _direct style_ implementation of futures would be used liked this:

```scala
  val sum = Future:
    val f1 = Future(c1.read)
    val f2 = Future(c2.read)
    f1.value + f2.value
```

In both cases, the two futures are executing in parallel, which might take a while. The
sum of the returned values is returned in a new future. The `value` method returns the result of a future once it is available or it throws an exception if the future returns a `Failure`. However, using the `boundary` and `break` mechanism discussed below, this exception would be caught by the `Future` library and used to cancel the other running `Future`, if one is still running, and then return a "failed" future for `sum`.

So, direct style simplifies code, making it is easier to write and understand, plus it enables cleaner separation of concerns, such as handling timeouts and failures in futures, and it cleanly supports composability, which monads don't provide unless you use cumbersome machinary such as monad transformers.

## Implementing Direct Style in Scala

In the talk, Martin discusses four aspects of building support for direct style in Scala:

1. `boundary` and `break` - now available in Scala 3.3.0.
2. Error handling - enabled, but not yet used in the library, which is still the Scala 2.13 library.
3. Suspensions - work in progress.
3. Concurrent library design built on the above - work in progress.

## `boundary` and `break`

This mechanism is defined with a new addition to the API, [`scala.util.boundary$`](https://www.scala-lang.org/api/3.3.0/scala/util/boundary$.html). It provides a cleaner alternative to non-local returns.

* `boundary` defines a context for a computation.
* `break` returns a value from within the enclosing boundary.

Here is an example from Martin's talk, which is also discussed in the [API](https://www.scala-lang.org/api/3.3.0/scala/util/boundary$.html):

```scala
import scala.util.boundary, boundary.break

def firstIndex[T](xs: List[T], elem: T): Int =
  boundary:
    for (x, i) <- xs.zipWithIndex do
      if x == elem then break(i)
    -1
```

`BoundaryExamples.scala` in this directory also implements this method. (There is a test for it in `.../test/scala/concurrency/direct/BoundaryExamplesSuite.scala`.)

As shown, `break` can optionally return a value.

Here is a slightly simplified implementation. (The full 3.3.0 source code is [here](https://github.com/lampepfl/dotty/blob/3.3.0/library/src/scala/util/boundary.scala)):

```scala
package scala.util

object boundary:
  final class Label[-T]

  inline def apply[T](inline body: Label[T] ?=> T): T = ...

  def break[T](value: T)(using label: Label[T]): Nothing =
    throw Break(label, value)

  final class Break[T] (val label: Label[T], val value: T) extends RuntimeException(...)

end boundary
```

In the example above, the `boundary:` line is short for `boundary.apply:` with the indented code below it passed as the body. 

Well actually, the `block` passed to `apply` is a _context function_ that is called within `apply` to return the block of code shown in the example. Note the `final class Label[T]` declaration in `boundary`. Users don't define `Label` instances themselves. Instead, this is done by the implementation of `boundary.apply` to provide the _capability_ of doing a non-local return. Using a `Label` in this way prevents the user from trying to call `break` without an enclosing `boundary`. 

Rephrasing all that, we don't want users to call `break` without an enclosing `boundary`. That's why `break` requires an in-scope given instance of `Label`, which the implementation of `boundary.apply` creates before it calls `body` (not shown). When your code block is executed, if it calls `break`, a given `Label` is in-scope. 

You don't have to do anything to create the context function passed to `boundary.apply`. It is synthesized from your block of code automatically when `boundary.apply` is called. 

Look at `firstIndex()` again. If we do find an element that is equal to `elem`, then we call break to return the index `i` from the `boundary`. If we don't find the element, then a "normal" return is used to return `-1`. We never reach the `-1` expression if `break` is called.

This directory includes a second example, `optional.scala` that is discussed in the video. See the comments in that file for details and an example of usage in `BoundaryExamples.scala`.

### Other Benefits

This implementation is a better alternative to `scala.util.control.NonLocalReturns` and `scala.util.control.Breaks` which are deprecated as of Scala 3.3.0. The new feature is easier for developers to use and it adds the following additional benefits:

* The implementation uses a new [`scala.util.boundary$.Break`](https://www.scala-lang.org/api/3.3.0/scala/util/boundary$$Break.html) class that derives from `RuntimeException`. Therefore, `break`s are logically non-fatal exceptions and the implementation is optimized to suppress unnecessary stack trace generation.
* Better performance is provided when a `break` occurs to the enclosing scope inside the same method (i.e., the same stack frame), where it can be rewritten to a jump call.

## A New Concurrency Library

Back to the futures :) , `boundary` and `break` can be used for adding new concurrency abstractions to Scala following a direct style, like the `Futures` example above. The implementation is not trivial for Scala, because,

1. Scala now runs on three platforms: JVM, JavaScript, and native.
2. Even on the JVM, using the new lightweight fibers coming in [Project Loom](https://wiki.openjdk.org/display/loom/Main) would only be available to users on the most recent JVMs (19 and later).

Possible implementation approaches include using source or bytecode rewriting.

So, the implementation will be non-trivial, but work has started in the [`lampepfl/async`](https://github.com/lampepfl/async) repo, a "strawman" for ideas, both for conceptual abstractions for concurrency (like a new `Future` type), as well as implementations.

### A Comparison with Ray

The direct style for `Futures` above looks a lot like working with tasks and actors in [Ray](https://ray.io), the Python-centric concurrency library that is becoming popular for computationally-heavy projects, like ML/AI. [I really like that API](https://medium.com/distributed-computing-with-ray/ray-for-the-curious-fa0e019e17d3) for its simplicity and concision for users. The Ray abstractions  heavily rely on the metaprogramming flexibility in a dynamically-typed language like Python, while the highly-scalable, backend services for distributed computation are written in C++.

In contrast, the Scala abstractions are based on more principled and flexible foundations that are part of Scala 3 itself (discussed below), which keep the implementations very concise. However, the implementations target different users. Ray is designed for large-scale cluster computation, as well as local multi-threading. The equivalent in Scala would require more extensive backend libraries, such as an optional Akka implementation.
