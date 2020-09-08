// src/test/scala/progscala3/implicits/json/ToJSONNewTypeClassSuite.scala
package progscala3.implicits.json

import scala.language.implicitConversions
import progscala3.implicits.{Address, Person}
import munit._

class ToJSONNewTypeClassSuite extends FunSuite:

  val address = Address("1 Scala Lane", "Anytown")
  val person = Person("Buck Trends", address)

  def ns(s: String) = s.replaceAll("\\s+", "")  // remove white space

  test("An extension method is a good way to add a custom toJSON method") {
    assert(ns(address.toJSON2(0)) == ns("""{
      |  "street": "1 Scala Lane",
      |  "city":   "Anytown"
      |}""".stripMargin))
    assert(ns(person.toJSON2(0)) == ns("""{
      |  "name":    "Buck Trends",
      |  "address": {
      |    "street": "1 Scala Lane",
      |    "city":   "Anytown"
      |  }
      |}""".stripMargin))
  }
