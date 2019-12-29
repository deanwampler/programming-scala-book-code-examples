// src/main/scala/progscala3/fp/recursion/trampoline.sc
// From: scala-lang.org/api/current/index.html#scala.util.control.TailCalls$
import scala.util.control.TailCalls._

def isEven(xs: Seq[Int]): TailRec[Boolean] =
  if (xs.isEmpty) done(true) else tailcall(isOdd(xs.tail))

def isOdd(xs: Seq[Int]): TailRec[Boolean] =
 if (xs.isEmpty) done(false) else tailcall(isEven(xs.tail))

val eo = (1 to 5).map(i => (i, isEven(1 to i).result))
assert(eo == Seq(1 -> false, 2 -> true, 3 -> false, 4 -> true, 5 -> false))
