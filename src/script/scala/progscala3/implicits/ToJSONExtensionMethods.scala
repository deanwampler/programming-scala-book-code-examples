// src/script/scala/progscala3/implicits/ToJSONExtensionMethods.scala

import progscala3.implicits.{Address, Person}

object ToJSONUtil:                                                 // <1>
  val INDENTATION = "  "
  def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

extension (address: Address):
  def toJSON3(level: Int): String =               // <2>
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

val address = Address("1 Scala Lane", "Anytown")
val person = Person("Buck Trends", address)

address.toJSON3(0)
person.toJSON3(0)
