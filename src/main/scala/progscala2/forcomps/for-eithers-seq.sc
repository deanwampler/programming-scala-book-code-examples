// src/main/scala/progscala2/forcomps/for-eithers-seq.sc
import scala.util.{ Either, Left, Right }

val results: Seq[Either[RuntimeException,Int]] =
  Vector(Right(10), Left(new RuntimeException("boo!")), Right(20))

val results2a = for {
  Right(i) <- results
} yield (2 * i)
// Returns: results2a: Seq[Int] = Vector(20, 40)
