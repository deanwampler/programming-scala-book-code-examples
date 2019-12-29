// src/main/scala/progscala3/implicits/implicit-conversions-resolution.sc
// Add the following if you don't use the flag -language:implicitConversions
// import scala.language.implicitConversions

// WARNING: You must :paste mode in the REPL for the following.
// Using :load won't compile the two definitions together!
case class Foo(s: String)
object Foo {
  implicit def fromString(s: String): Foo = Foo(s"implicit conversion: $s")
}

object O {
  import Foo._
  def mf(foo: Foo): String  = foo.s
  def ms(s: String): String = mf(s)
}

assert(O.mf(Foo("mf")) == "mf")
assert(O.ms("ms") == "implicit conversion: ms")
