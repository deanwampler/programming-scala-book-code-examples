// src/main/scala/progscala3/concurrency/direct/Suspension.scala
package progscala3.concurrency.boundary

import scala.util.boundary, boundary.break, boundary.Label

// A.k.a. "delimited continuations".
// Can be used expressed to express algebraic effects and monads.
class Suspension[-T, +R]:
  def resume(arg: T): R = ???

  def suspend[T, R](body: Suspension[T, R] => R)(using Label[R]): T = ???