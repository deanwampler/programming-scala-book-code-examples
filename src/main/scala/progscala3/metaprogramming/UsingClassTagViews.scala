// src/main/scala/progscala3/metaprogramming/UsingClassTagViews.scala
package progscala3.metaprogramming

import scala.reflect.ClassTag

object UsingClassTagViews {

	/**
	 * A method that makes a sequence of the proper
	 * element type, by leveraging a ClassTag view of
	 * the element type.
	 */
	def mkSeq[T : ClassTag](elems: T*) = Seq[T](elems: _*)

	def main(args: Array[String]): Unit = {
		assert(mkSeq(1, 2, 3) == List[Int](1, 2, 3))
		assert(mkSeq("one", "two", "three") == List[String]("one", "two", "three"))
		assert(mkSeq(1, "two", 3.14) == List[Any](1, "two", 3.14))
		println("success!")
	}
}
