// src/main/scala/progscala3/forcomps/ForOptionsBad2.scala
package progscala3.forcomps

/** Another bad example, when easier idioms exist. */
object ForOptionsBad2 {
  def main(args: Array[String]): Unit = {

		val successfulCounts = Seq(Some(5), Some(21), Some(8))
		val partiallySuccessfulCounts = Seq(Some(5), None, Some(8))

		def sumCountsBad(counts: Seq[Option[Int]]): Option[Int] =
		  (counts foldLeft Option(0)) {
		    (countOption, count) =>
		      if (countOption == None || count == None) None
		      else Some(countOption.get + count.get)
		  }

		assert(sumCountsBad(successfulCounts) == Some(34))
		assert(sumCountsBad(partiallySuccessfulCounts) == None)
	}
}
