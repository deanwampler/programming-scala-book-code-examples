// tag::file[]
// src/script/scala/progscala3/typelessdomore/OptionalNew.scala

import java.io.File

val file = File("README.md")    // No "new" required
// end::file[]

// tag::anon[]
trait Welcome:
  def hello(name: String): Unit

val hi = new Welcome:
  def hello(name: String): Unit = println(s"Hello: $name")
// end::anon[]
