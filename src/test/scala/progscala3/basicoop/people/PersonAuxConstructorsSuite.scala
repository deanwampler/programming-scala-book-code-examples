// src/main/scala/progscala3/basicoop/people/PersonAuxConstructorsSuite.scala
package progscala3.basicoop.people

import munit._

class PersonAuxConstructorsSuite extends FunSuite {

  val addr = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))

  test("PersonAuxConstructors.toString returns a human-readable string") {
    assert(addr.toString == "Address(1 Scala Lane,Bay Area,CA,98765)")
  }

  test("Age is optional") {
    val withAge = new PersonAuxConstructors("Buck Trends", 20)
    assert(withAge.name    == "Buck Trends")
    assert(withAge.age     == Some(20))

    val withoutAge = new PersonAuxConstructors("Buck Trends")
    assert(withoutAge.name    == "Buck Trends")
    assert(withoutAge.age     == None)
  }

  test("Address is optional") {
    val withAge = new PersonAuxConstructors("Buck Trends", addr)
    assert(withAge.name    == "Buck Trends")
    assert(withAge.address == Some(addr))

    val withoutAge = new PersonAuxConstructors("Buck Trends")
    assert(withoutAge.name    == "Buck Trends")
    assert(withoutAge.address == None)
  }

  test("Age and address can be specified") {
    val p = new PersonAuxConstructors("Buck Trends", 20, addr)
    assert(p.name    == "Buck Trends")
    assert(p.age     == Some(20))
    assert(p.address == Some(addr))
  }
}
