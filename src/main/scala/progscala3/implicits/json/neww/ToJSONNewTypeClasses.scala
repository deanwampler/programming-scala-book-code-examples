// src/main/scala/progscala3/implicits/json/neww/ToJSONNewTypeClasses.scala
package progscala3.implicits.json.neww   // Can't use keyword "new"!

import progscala3.implicits.{Address, Person}
import progscala3.implicits.json._
import scala.language.implicitConversions                            // <1>

given Conversion[Address, ToJSON]:                                   // <2>
  def apply(address: Address): ToJSON = new ToJSON:
    def toJSON(level: Int): String =
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"street": "${address.street}",
        |${indent}"city":   "${address.city}"
        |$outdent}""".stripMargin

given Conversion[Person, ToJSON]:                                    // <3>
  def apply(person: Person): ToJSON = new ToJSON:
    def toJSON(level: Int): String =
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"name":    "${person.name}",
        |${indent}"address": ${person.address.toJSON(level + 1)}
        |$outdent}""".stripMargin

@main def TryJSONNewTypeClasses() =                                  // <4>
  val address = Address("1 Scala Lane", "Anytown")
  val person = Person("Buck Trends", address)
  println(s"address: $address vs. ${address.toJSON(0)}")
  println(s"person: $person vs. ${person.toJSON(0)}")
