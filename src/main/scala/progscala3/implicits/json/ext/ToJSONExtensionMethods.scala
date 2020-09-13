// src/main/scala/progscala3/implicits/json/ext/ToJSONExtensionMethods.scala

package progscala3.implicits.json.ext

import progscala3.implicits.{Address, Person}
import scala.language.implicitConversions

object ToJSONUtil:                                                   // <1>
  val INDENTATION = "  "
  def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

extension (address: Address):
  def toJSON(level: Int): String =                                   // <2>
    val (outdent, indent) = ToJSONUtil.indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin

extension (person: Person):
  def toJSON(level: Int): String =
    val (outdent, indent) = ToJSONUtil.indentation(level)
    s"""{
      |${indent}"name":    "${person.name}",
      |${indent}"address": ${person.address.toJSON(level + 1)}
      |$outdent}""".stripMargin

@main def TryJSONExtensionMethods() =
  val address = Address("1 Scala Lane", "Anytown")
  val person = Person("Buck Trends", address)
  println(s"address: $address vs. ${address.toJSON(0)}")
  println(s"person: $person vs. ${person.toJSON(0)}")
