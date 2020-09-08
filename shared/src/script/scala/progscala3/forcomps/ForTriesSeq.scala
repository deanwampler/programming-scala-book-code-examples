// src/script/scala/progscala3/forcomps/ForTriesSeq.scala
import scala.util.{ Try, Success, Failure }

val tries: Seq[Try[Int]] =
  Vector(Success(10), Failure(new RuntimeException("boo!")), Success(20))

val ints = for
  case Success(i) <- tries
yield (2 * i)
