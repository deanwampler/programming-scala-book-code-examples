// src/test/scala/progscala3/contexts/ImplicitConversionResolutionSuite.scala
package progscala3.contexts

import munit.*
import scala.language.implicitConversions

class ImplicitConversionResolutionSuite extends FunSuite:

  case class Foo(s: String)
  object Foo:
    /* DeanW: September 2025. Scala is dropping support for implicit classes, 
     * so we use a given Conversion instead:
     * implicit def fromString(s: String): Foo =
     *   Foo(s"Foo's implicit conversion: $s")
     */
    given Conversion[String, Foo] = 
      s => Foo(s"Foo's implicit conversion: $s")

  object scope1:
    import Foo.*
    def apply(foo: Foo): String  = foo.s

  object scope2:
    object implicits:
      /* DeanW: September 2025. Scala is dropping support for implicit classes, 
       * so we use a given Conversion instead:
       * implicit def overridingConversion(s: String): Foo =
       *   Foo(s"scope2's implicit conversion: $s")
       */
      given Conversion[String, Foo] = 
        s => Foo(s"scope2's implicit conversion: $s")

    def apply(foo: Foo): String  = foo.s

  test("Implicit conversion method in scope is invoked to convert a type"){
    assert(scope1(Foo("no use of conversion")) == "no use of conversion")
    assert(scope1("foo") == "Foo's implicit conversion: foo")
  }

  test("The closest implicit conversion in scope is invoked") {
    // DeanW - September 2025: Now we need to import the given:
    // import scope2.implicits.*
    import scope2.implicits.given
    assert(scope2(Foo("no use of conversion")) == "no use of conversion")
    assert(scope2("foo") == "scope2's implicit conversion: foo", scope2("foobar"))
  }
