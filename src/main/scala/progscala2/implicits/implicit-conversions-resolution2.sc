// src/main/scala/progscala2/implicits/implicit-conversions-resolution2.sc
import scala.language.implicitConversions

case class Foo(s: String)
object Foo {
  implicit def fromString(s: String): Foo = Foo(s)
}

implicit def overridingConversion(s: String): Foo = Foo("Boo: "+s)

class O {
  def m1(foo: Foo) = println(foo)
  def m(s: String) = m1(s)
}
