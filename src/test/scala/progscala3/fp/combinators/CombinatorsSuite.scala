// src/test/scala/progscala3/fp/combinators/CombinatorsSuite.scala
package progscala3.fp.combinators

import munit._
import scala.language.higherKinds                         // <1>

class CombinatorsSuite extends FunSuite {

	/** A map definition that takes the collection first, then the function. */
	object CombinatorsLF {
	  def map[A,B](list: List[A])(f: (A) => B): List[B] = list map f
	}

	/** A map definition that takes the function first, then the collection. */
	object CombinatorsFL {
	  def map[A,B](f: (A) => B)(list: List[A]): List[B] = list map f
	}

	val intToString = (i:Int) => s"N=$i"

	val input = List(1, 2, 3, 4)
	val expected = List[String]("N=1", "N=2", "N=3", "N=4")

	test("A function-first combinator is easier to use for defining functions") {
		val f = CombinatorsFL.map(intToString)
		val list = f(input)
		assert(list == expected)
	}

	test("A collection-first combinator is easier to use 'all at once'") {
		val list = CombinatorsLF.map(input)(intToString)
		assert(list == expected)
	}
}

