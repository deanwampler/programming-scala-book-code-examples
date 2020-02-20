// src/main/scala/progscala3/forcomps/ForTriesSeq.scala
package progscala3.forcomps
import scala.util.{ Try, Success, Failure }

object ForTriesSeq {
  def main(args: Array[String]): Unit = {
		val results: Seq[Try[Int]] =
		  Vector(Success(10), Failure(new RuntimeException("boo!")), Success(20))
		println(results)

		val results2a = for {
		  case Success(i) <- results
		} yield (2 * i)
		assert(results2a == Vector(20, 40))
	}
}
