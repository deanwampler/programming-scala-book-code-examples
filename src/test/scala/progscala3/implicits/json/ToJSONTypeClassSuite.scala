// src/test/scala/progscala3/implicits/ToJSONTypeClassSuite.scala
package progscala3.implicits

import munit._

class ToJSONTypeClassSuite extends FunSuite:

  sealed trait DomainConcept                                            // <1>
  case class Address(street: String, city: String)  extends DomainConcept
  case class Person(name: String, address: Address) extends DomainConcept

  trait ToJSON:                                                         // <2>
    def toJSON(level: Int = 0): String

    val INDENTATION = "  "
    def indentation(level: Int = 0): (String,String) =
      (INDENTATION * level, INDENTATION * (level+1))

  implicit final class AddressToJSON(address: Address) extends ToJSON:  // <3>
    def toJSON(level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"street": "${address.street}",
        |${indent}"city":   "${address.city}"
        |$outdent}""".stripMargin

  implicit final class PersonToJSON(person: Person) extends ToJSON:     // <4>
    def toJSON(level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"name":    "${person.name}",
        |${indent}"address": ${person.address.toJSON(level + 1)}
        |$outdent}""".stripMargin

  val address = Address("1 Scala Lane", "Anytown")
  val person = Person("Buck Trends", address)

  def ns(s: String) = s.replaceAll("\\s+", "")  // remove white space

  test("An extension method is a good way to add a custom toJSON method") {
    assert(ns(address.toJSON()) == ns("""{
      |  "street": "1 Scala Lane",
      |  "city":   "Anytown"
      |}""".stripMargin))
    assert(ns(person.toJSON()) == ns("""{
      |  "name":    "Buck Trends",
      |  "address": {
      |    "street": "1 Scala Lane",
      |    "city":   "Anytown"
      |  }
      |}""".stripMargin))
  }
