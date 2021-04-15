// src/main/scala/progscala3/meta/Invariant1.scala
package progscala3.meta

object invariant1:
  inline val ignore = false                                     // <1>

  /**
   * Throw an exception if the predicate is false before the block is
   * evaluated, then evaluate the block, then check the predicate again.
   * If all predicate checks pass, then return the block's value.
   */
  inline def apply[T](                                          // <2>
      inline predicate: => Boolean)(
      inline block: => T): T =
    inline if !ignore then                                      // <3>
      if !predicate then throw InvariantFailure("before")
      val result = block
      if !predicate then throw InvariantFailure("after")
      result
    else
      block                                                     // <4>

  case class InvariantFailure(beforeAfter: String) extends RuntimeException(
    s"FAILURE! predicate failed $beforeAfter evaluation!")

@main def TryInvariant1 =
  var i = 0
  invariant1(i >= 0)(i += 1)
  println(s"success: $i")
  println(s"Will now fail:")
  invariant1(i >= 0)(i -= 2)                                    // <5>
