// src/main/scala/progscala2/forcomps/for-tries-seq.sc
import scala.util.{ Try, Success, Failure }

val results: Seq[Try[Int]] =
  Vector(Success(10), Failure(new RuntimeException("boo!")), Success(20))

val results2a = for {
  Success(i) <- results
} yield (2 * i)
// Returns: results2a: Seq[Int] = Vector(20, 40)
