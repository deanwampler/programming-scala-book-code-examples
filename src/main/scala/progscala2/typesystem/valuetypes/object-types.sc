// src/main/scala/progscala2/typesystem/valuetypes/object-types.sc

case object Foo { override def toString = "Foo says Hello!" }

def printFoo(foo: Foo.type) = println(foo)

printFoo(Foo)                          // "Foo says Hello!"


case class C(s: String)
val c1 = C("c1")

val c1b: c1.type = c1
val c1b: c1.type = C("c1b")            // Error
