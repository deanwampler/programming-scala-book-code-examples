// src/script/scala/progscala3/fp/recursion/Trampoline.scala

import scala.util.control.TailCalls.*

def isEven(xs: Seq[Int]): TailRec[Boolean] =
  if xs.isEmpty then done(true) else tailcall(isOdd(xs.tail))

def isOdd(xs: Seq[Int]): TailRec[Boolean] =
  if xs.isEmpty then done(false) else tailcall(isEven(xs.tail))

val eo = (1 to 5).map(i => (i, isEven(1 to i).result))
