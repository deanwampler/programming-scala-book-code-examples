// src/main/scala/progscala3/concurrency/direct/optional.scala
package progscala3.concurrency.boundary

import scala.util.boundary, boundary.break, boundary.Label

/**
 * Try a computation that will hopefully return `Some(thing)`, but if it can't
 * return the `thing`, then return `None`. 
 * See an example usage in BoundaryExamples.scala
 */
object optional:
  /**
   * Like `boundary.apply()` this method defines a boundary for the computation.
   * The reason the `Label` is typed with `None.type`, is because if we call `break`,
   * it is because we are returning `None` to the boundary, not `Some(thing)` that we
   * would otherwise return.
   */
  inline def apply[T](inline body: Label[None.type] ?=> T): Option[T] =
    boundary(Some(body))

  /**
   * Inspired by a similar-looking construct in Rust, if you have an `Option` instance,
   * calling `?` on it will either return the enclosed object or break to the enclosing
   * boundary with `None` as the value returned at the boundary.
   */
  extension [T](r: Option[T])
    inline def ? (using label: Label[None.type]): T = r match
      case Some(x) => x
      case None => break(None)
