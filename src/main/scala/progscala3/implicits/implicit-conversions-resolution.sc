// src/main/scala/progscala3/implicits/implicit-conversions-resolution.sc
// Add the following if you don't use the flag -language:implicitConversions
// import scala.language.implicitConversions

// WARNING: You must :paste mode in the REPL for the following.
// Using :load won't compile the two definitions together!
case class Foo(s: String)
object Foo {
  implicit def fromString(s: String): Foo = Foo(s)
}

class O {
  def m1(foo: Foo) = println(foo)
  def m(s: String) = m1(s)
}
