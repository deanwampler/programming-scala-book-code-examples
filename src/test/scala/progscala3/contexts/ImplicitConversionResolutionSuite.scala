// src/test/scala/progscala3/contexts/ImplicitConversionResolutionSuite.scala
package progscala3.contexts

import munit.*
import scala.language.implicitConversions

class ImplicitConversionResolutionSuite extends FunSuite:

  case class Foo(s: String)
  object Foo:
    implicit def fromString(s: String): Foo =
      Foo(s"Foo's implicit conversion: $s")

  object scope1:
    import Foo.*
    def apply(foo: Foo): String  = foo.s

  object scope2:
    object implicits:
      implicit def overridingConversion(s: String): Foo =
        Foo(s"scope2's implicit conversion: $s")

    def apply(foo: Foo): String  = foo.s

  test("Implicit conversion method in scope is invoked to convert a type"){
    assert(scope1(Foo("no use of conversion")) == "no use of conversion")
    assert(scope1("foo") == "Foo's implicit conversion: foo")
  }

  test("The closest implicit conversion in scope is invoked") {
    import scope2.implicits.*
    assert(scope2(Foo("no use of conversion")) == "no use of conversion")
    assert(scope2("foo") == "scope2's implicit conversion: foo")
  }
