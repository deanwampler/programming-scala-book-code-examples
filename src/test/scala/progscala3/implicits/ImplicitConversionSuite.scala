// src/test/scala/progscala3/implicits/ImplicitConversionSuite.scala
package progscala3.implicits

import munit._
import scala.annotation.alpha

class ImplicitConversionSuite extends FunSuite:

	object implicits:
	  implicit final class TIEFighter[A](val self: A):
	    @alpha("tie_fighter") def <-*-> [B](y: B): Tuple2[A, B] =
	    	Tuple2(self, y)

	import implicits._

	test("An implicit conversion class in scope is invoked when needed") {
		val m = Map("one" <-*-> 1, "two" <-*-> 2)
		assert(m == Map("one" -> 1, "two" -> 2))
	}
