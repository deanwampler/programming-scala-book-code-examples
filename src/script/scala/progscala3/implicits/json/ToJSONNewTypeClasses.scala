// src/script/scala/progscala3/implicits/json/ToJSONNewTypeClasses.scala

import progscala3.implicits.{Address, Person}
import scala.language.implicitConversions

trait ToJSON2:                                                  // <1>
  def toJSON2(level: Int): String

  val INDENTATION = "  "
  def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

given Conversion[Address, ToJSON2]:                             // <2>
  def apply(address: Address): ToJSON2 = new ToJSON2:
    def toJSON2(level: Int): String =
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"street": "${address.street}",
        |${indent}"city":   "${address.city}"
        |$outdent}""".stripMargin

given Conversion[Person, ToJSON2]:                              // <3>
  def apply(person: Person): ToJSON2 = new ToJSON2:
    def toJSON2(level: Int): String =
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"name":    "${person.name}",
        |${indent}"address": ${person.address.toJSON2(level + 1)}
        |$outdent}""".stripMargin

val address = Address("1 Scala Lane", "Anytown")
val person = Person("Buck Trends", address)

address.toJSON2(0)
person.toJSON2(0)
