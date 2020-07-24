// src/test/scala/progscala3/implicits/ToJSONExtensionMethodSuite.scala
package progscala3.implicits

import munit._
import scala.language.implicitConversions

class ToJSONExtensionMethodSuite extends FunSuite {

  sealed trait DomainConcept
  case class Address(street: String, city: String)  extends DomainConcept
  case class Person(name: String, address: Address) extends DomainConcept

  object ToJSONUtil {                                                // <1>
    val INDENTATION = "  "
    def indentation(level: Int = 0): (String,String) =
      (INDENTATION * level, INDENTATION * (level+1))
  }

  def (address: Address) toJSON(level: Int): String = {              // <2>
    val (outdent, indent) = ToJSONUtil.indentation(level)
    s"""{
      |${indent}"street": "${address.street}",
      |${indent}"city":   "${address.city}"
      |$outdent}""".stripMargin
  }

  def (person: Person) toJSON(level: Int): String = {
    val (outdent, indent) = ToJSONUtil.indentation(level)
    s"""{
      |${indent}"name":    "${person.name}",
      |${indent}"address": ${person.address.toJSON(level + 1)}
      |$outdent}""".stripMargin
  }

  val address = Address("1 Scala Lane", "Anytown")
  val person = Person("Buck Trends", address)

  def ns(s: String) = s.replaceAll("\\s+", "")  // remove white space

  test("An extension method is a good way to add a custom toJSON method") {
    assert(ns(address.toJSON(0)) == ns("""{
      |  "street": "1 Scala Lane",
      |  "city":   "Anytown"
      |}""".stripMargin))
    assert(ns(person.toJSON(0)) == ns("""{
      |  "name":    "Buck Trends",
      |  "address": {
      |    "street": "1 Scala Lane",
      |    "city":   "Anytown"
      |  }
      |}""".stripMargin))
  }
}
