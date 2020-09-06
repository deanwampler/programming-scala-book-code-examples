// src/main/scala/progscala3/implicits/json/ToJSONExtensionMethods.scala

package progscala3.implicits.json

import progscala3.implicits.{Address, Person}
import scala.language.implicitConversions

object ToJSONUtil:                                                   // <1>
  val INDENTATION = "  "
  def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

extension (address: Address):
  def toJSON3(level: Int): String =                                  // <2>
    val (outdent, indent) = ToJSONUtil.indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin

extension (person: Person):
  def toJSON3(level: Int): String =
    val (outdent, indent) = ToJSONUtil.indentation(level)
    s"""{
      |${indent}"name":    "${person.name}",
      |${indent}"address": ${person.address.toJSON3(level + 1)}
      |$outdent}""".stripMargin

@main def TryJSONExtensionMethods() =
  val address = Address("1 Scala Lane", "Anytown")
  val person = Person("Buck Trends", address)
  println(s"address: $address vs. ${address.toJSON3(0)}")
  println(s"person: $person vs. ${person.toJSON3(0)}")
