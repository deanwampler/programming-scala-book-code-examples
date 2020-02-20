// src/main/scala/progscala3/forcomps/ForVariableTranslated.scala
package progscala3.forcomps

object ForVariableTranslated {
  def main(args: Array[String]): Unit = {

		val map = Map("one" -> 1, "two" -> 2)

		val list1 = for {
		  (key, value) <- map   // How are this line and the next one translated?
		  i10 = value + 10
		} yield (i10)
		assert(list1 == List(11, 12))

		// Translation:
		val list2 = for {
		  (i, i10) <- for {
		    x1 @ (key, value) <- map
		  } yield {
		    val x2 @ i10 = value + 10
		    (x1, x2)
		  }
		} yield (i10)
		assert(list2 == List(11, 12))
	}
}
