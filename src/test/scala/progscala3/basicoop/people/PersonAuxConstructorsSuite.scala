// src/main/scala/progscala3/basicoop/people/PersonAuxConstructorsSuite.scala
package progscala3.basicoop.people

import munit._
import progscala3.metaprogramming.requirement

class PersonAuxConstructorsSuite extends FunSuite {

  val addr = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))

  test("PersonAuxConstructors.toString returns a human-readable string") {
    requirement(addr.toString == "Address(1 Scala Lane,Bay Area,CA,98765)")
  }

  test("Age is optional") {
    val withAge = new PersonAuxConstructors("Buck Trends", 20)
    requirement(withAge.name    == "Buck Trends")
    requirement(withAge.age     == Some(20))

    val withoutAge = new PersonAuxConstructors("Buck Trends")
    requirement(withoutAge.name    == "Buck Trends")
    requirement(withoutAge.age     == None)
  }

  test("Address is optional") {
    val withAge = new PersonAuxConstructors("Buck Trends", addr)
    requirement(withAge.name    == "Buck Trends")
    requirement(withAge.address == Some(addr))

    val withoutAge = new PersonAuxConstructors("Buck Trends")
    requirement(withoutAge.name    == "Buck Trends")
    requirement(withoutAge.address == None)
  }

  test("Age and address can be specified") {
    val p = new PersonAuxConstructors("Buck Trends", 20, addr)
    requirement(p.name    == "Buck Trends")
    requirement(p.age     == Some(20))
    requirement(p.address == Some(addr))
  }
}
