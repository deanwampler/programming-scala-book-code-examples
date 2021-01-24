// src/script/scala/progscala3/forcomps/ForEithersSeq.scala

val seq: Seq[Either[RuntimeException,Int]] =
  Vector(Right(10), Left(RuntimeException("boo!")), Right(20))

val results2 = for
  case Right(i) <- seq
yield 2 * i
