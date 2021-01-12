// src/script/scala/progscala3/typesystem/valuetypes/SingletonTypes.scala

case object Foo:
  override def toString = "Foo says Hello!"

def fooString(foo: Foo.type) = s"Foo.type: $foo"                // <1>

case class C(s: String)
val c1 = C("c1")
println(c1)
val c1b: c1.type = c1                                           // <2>
println(c1b)
val c1c: c1.type = C("c1")                                      // <3>
