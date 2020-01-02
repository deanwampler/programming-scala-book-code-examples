// src/main/scala/progscala3/forcomps/for-tries-seq.sc
import scala.util.{ Try, Success, Failure }

val results: Seq[Try[Int]] =
  Vector(Success(10), Failure(new RuntimeException("boo!")), Success(20))
println(results)

val results2a = for {
  Success(i) <- results
} yield (2 * i)
assert(results2a == Vector(20, 40))
