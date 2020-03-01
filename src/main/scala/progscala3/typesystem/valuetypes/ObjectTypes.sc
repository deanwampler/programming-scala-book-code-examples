// src/main/scala/progscala3/typesystem/valuetypes/ObjectTypes.sc

case object Foo { override def toString = "Foo says Hello!" }

def fooString(foo: Foo.type) = s"Foo.type: $foo"

assert(fooString(Foo) == "Foo.type: Foo says Hello!")


case class C(s: String)
val c1 = C("c1")
println(c1)
val c1b: c1.type = c1
println(c1b)
// val c1b: c1.type = C("c1b")            // Error
