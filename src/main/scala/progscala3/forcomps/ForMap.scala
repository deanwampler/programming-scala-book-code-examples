// src/main/scala/progscala3/forcomps/ForMap.scala
package progscala3.forcomps

object ForMap {
  def main(args: Array[String]): Unit = {
		val states = List("Alabama", "Alaska", "Virginia", "Wyoming")
		val results1 = for {
		  s <- states
		} yield s.toUpperCase
		println(results1)
		assert(results1 == List("ALABAMA", "ALASKA", "VIRGINIA", "WYOMING"))

		val results2 = states map (_.toUpperCase)
		println(results2)
		assert(results1 == results2)
	}
}
