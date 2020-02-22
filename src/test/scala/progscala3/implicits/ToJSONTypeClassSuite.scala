// src/test/scala/progscala3/implicits/ToJSONTypeClassSuite.scala
package progscala3.implicits

import munit._
import scala.language.implicitConversions

class ToJSONTypeClassSuite extends FunSuite {

  case class Address(street: String, city: String)
  case class Person(name: String, address: Address)

  trait ToJSON {
    def toJSON(level: Int = 0): String

    val INDENTATION = "  "
    def indentation(level: Int = 0): (String,String) =
      (INDENTATION * level, INDENTATION * (level+1))
  }

  implicit class AddressToJSON(address: Address) extends ToJSON {
    def toJSON(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"street": "${address.street}",
        |${indent}"city":   "${address.city}"
        |$outdent}""".stripMargin
    }
  }

  implicit class PersonToJSON(person: Person) extends ToJSON {
    def toJSON(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
        |${indent}"name":    "${person.name}",
        |${indent}"address": ${person.address.toJSON(level + 1)}
        |$outdent}""".stripMargin
    }
  }

  val a = Address("1 Scala Lane", "Anytown")
  val p = Person("Buck Trends", a)

  def ns(s: String) = s.replaceAll("\\s+", "")  // remove white space

  test("An extension method is a good way to add a custom toJSON method") {
    assert(ns(a.toJSON()) == ns("""{
      |  "street": "1 Scala Lane",
      |  "city":   "Anytown"
      |}""".stripMargin))
    assert(ns(p.toJSON()) == ns("""{
      |  "name":    "Buck Trends",
      |  "address": {
      |    "street": "1 Scala Lane",
      |    "city":   "Anytown"
      |  }
      |}""".stripMargin))
  }
}
