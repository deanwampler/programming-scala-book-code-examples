// tag::aux[]
// src/script/scala/progscala3/typelessdomore/OptionalNew.scala

class Person(name: String, age: Int):
  def this() = this("unknown", 0)                               // <1>
// end::aux[]

// tag::file[]
import java.io.File
val file = File("README.md")    // No "new" needed, even for Java classes!
// end::file[]

// tag::anon[]
trait Welcome:
  def hello(name: String): Unit

val hello = new Welcome:
  def hello(name: String): Unit = println(s"Hello: $name")
// end::anon[]
