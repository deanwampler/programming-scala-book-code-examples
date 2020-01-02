// src/main/scala/progscala3/forcomps/for-eithers-seq.sc
import scala.util.{ Either, Left, Right }

val results: Seq[Either[RuntimeException,Int]] =
  Vector(Right(10), Left(new RuntimeException("boo!")), Right(20))
println(results)

val results2 = for {
  Right(i) <- results
} yield (2 * i)
assert(results2 == Vector(20, 40))
