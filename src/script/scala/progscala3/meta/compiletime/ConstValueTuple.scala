// src/script/scala/progscala3/meta/compiletime/ConstValueTuple.scala
// Example mentioned in the book, but not discussed there.
import scala.compiletime.constValueTuple

// Creates tuples with the singleton types and corresponding values:
val goodTuple =
  constValueTuple["foo" *: 10 *: 2.5 *: EmptyTuple]

import compiletime.ops.int.*
val goodTuple2 =
  constValueTuple["foo" *: (10 + 1) *: 2.5 *: EmptyTuple]
