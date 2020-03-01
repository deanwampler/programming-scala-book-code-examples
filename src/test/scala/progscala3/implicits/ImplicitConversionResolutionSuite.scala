// src/test/scala/progscala3/implicits/ImplicitConversionResolutionSuite.scala
package progscala3.implicits

import munit._
import scala.language.implicitConversions

class ImplicitConversionResolutionSuite extends FunSuite {

	case class Foo(s: String)
	object Foo {
	  implicit def fromString(s: String): Foo =
	  	new Foo(s"implicit conversion: $s")
	}

	object implicits {
	  import Foo._
	  def mf(foo: Foo): String  = foo.s
	  def ms(s: String): String = mf(s)
	}

	object implicits2 {
	  implicit def overridingConversion(s: String): Foo =
	    new Foo(s"object implicits2 conversion: $s")

	  def mf(foo: Foo): String  = foo.s
	  def ms(s: String): String = mf(s)
	}

	test("An implicit conversion method in scope is invoked to convert a type"){
		assert(implicits.mf(Foo("mf")) == "mf")
		assert(implicits.ms("ms") == "implicit conversion: ms")
	}

	test("The closest implicit conversion in scope is invoked") {
		assert(implicits2.mf(Foo("mf")) == "mf")
		assert(implicits2.ms("ms") == "object implicits2 conversion: ms")
	}
}
