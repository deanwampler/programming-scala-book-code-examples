// src/main/scala/progscala3/forcomps/ForEithersSeq.scala
package progscala3.forcomps
import scala.util.{ Either, Left, Right }

object ForEithersSeq {
  def main(args: Array[String]): Unit = {
		val results: Seq[Either[RuntimeException,Int]] =
		  Vector(Right(10), Left(new RuntimeException("boo!")), Right(20))
		println(results)

		val results2 = for {
		  case Right(i) <- results
		} yield (2 * i)
		assert(results2 == Vector(20, 40))
	}
}
