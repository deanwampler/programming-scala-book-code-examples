// src/test/scala/progscala3/fp/categories/FunctorExampleSuite.scala
package progscala3.fp.categories

import munit._
import scala.language.higherKinds                         // <1>

class FunctorExampleSuite extends FunSuite:

	val fii: Int => Int       = i => i * 2
	val fid: Int => Double    = i => 2.1 * i
	val fds: Double => String = d => d.toString

	test("SeqF.map behaves like the collection's map") {
		assert(SeqF.map(Seq(1,2,3,4))(fii) == Seq(2, 4, 6, 8))
		assert(SeqF.map(Seq.empty[Int])(fii) == Nil)
	}

	test("OptionF.map behaves like the collection's map") {
		assert(OptionF.map(Some(2))(fii) == Some(4))
		assert(OptionF.map(Option.empty[Int])(fii) == None)
	}

	test("FunctionF.map behaves like the collection's map") {
		val fa = FunctionF.map(fid)(fds)                             // <2>
		assert(fa(2) == "4.2")
	}

	test("OptionF.map can be typed") {
		// val fb = FunctionF.map(fid)(fds)                             <3>
		val fb = FunctionF.map[Int,Double,String](fid)(fds)
		assert(fb(2) == "4.2")
	}

	test("Functions compose") {
		val fc = fds compose fid                                     // <4>
		assert(fc(2) == "4.2")
	}
