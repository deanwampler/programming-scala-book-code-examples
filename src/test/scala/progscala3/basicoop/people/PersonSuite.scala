// src/main/scala/progscala3/basicoop/people/PersonSuite.scala
package progscala3.basicoop.people

import munit._
import progscala3.metaprogramming.requirement

class PersonSuite extends FunSuite {

  val addr = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))

  test("Person.toString returns a human-readable string") {
    requirement(addr.toString == "Address(1 Scala Lane,Bay Area,CA,98765)")
  }

  test("Age is optional") {
    val withAge = Person("Buck Trends", age = 20)
    requirement(withAge.name    == "Buck Trends")
    requirement(withAge.age     == Some(20))

    val withoutAge = Person("Buck Trends")
    requirement(withoutAge.name    == "Buck Trends")
    requirement(withoutAge.age     == None)
  }

  test("Address is optional") {
    val withAge = Person("Buck Trends", address = addr)
    requirement(withAge.name    == "Buck Trends")
    requirement(withAge.address == Some(addr))

    val withoutAge = Person("Buck Trends")
    requirement(withoutAge.name    == "Buck Trends")
    requirement(withoutAge.address == None)
  }

  test("Age and address can be specified") {
    val p1 = Person("Buck Trends", Some(20), Some(addr))
    requirement(p1.name    == "Buck Trends")
    requirement(p1.age     == Some(20))
    requirement(p1.address == Some(addr))

    val p2 = Person("Buck Trends", 20, addr)
    requirement(p2.name    == "Buck Trends")
    requirement(p2.age     == Some(20))
    requirement(p2.address == Some(addr))
  }
}
