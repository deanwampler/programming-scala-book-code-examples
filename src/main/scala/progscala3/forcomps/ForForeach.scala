// src/main/scala/progscala3/forcomps/ForForeach.scala
package progscala3.forcomps

object ForForeach {
  def main(args: Array[String]): Unit = {

		val states = List("Alabama", "Alaska", "Virginia", "Wyoming")
		for {
		  s <- states
		} println(s)
		// Results:
		// Alabama
		// Alaska
		// Virginia
		// Wyoming

		states foreach println
		// Prints the same results.
	}
}