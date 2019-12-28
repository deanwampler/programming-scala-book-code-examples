// src/main/scala/progscala3/implicits/implicit-conversions-resolution2.sc
// Add the following if you don't use the flag -language:implicitConversions
// import scala.language.implicitConversions

// WARNING: You must :paste mode in the REPL for the following.
// Using :load won't compile the two definitions together!
case class Foo(s: String)
object Foo {
  implicit def fromString(s: String): Foo = new Foo(s)
}
import Foo._

object scope {
  implicit def overridingConversion(s: String): Foo = Foo("Boo: "+s)

  def m(foo: Foo) = println(foo) // assert(foo == Foo(expected), "1: "+foo.toString)

  def m2(string: String) = m(string)
}

def m(foo: Foo) = println(foo) // assert(foo == Foo("string"), "2: "+foo.toString)

m("string1")
scope.m("string2")
scope.m2("string3")
