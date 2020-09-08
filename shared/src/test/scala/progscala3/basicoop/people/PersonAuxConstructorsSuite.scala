// src/test/scala/progscala3/basicoop/people/PersonAuxConstructorsSuite.scala
package progscala3.basicoop.people

import munit._

class PersonAuxConstructorsSuite extends FunSuite:

  val addr = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))

  test("PersonAuxConstructors.toString returns a human-readable string") {
    assert(addr.toString == "Address(1 Scala Lane,Bay Area,CA,98765)")
  }

  test("Age is optional") {
    val withAge = new PersonAuxConstructors("Buck Trends", 20)
    assert(withAge.name.equals("Buck Trends"))
    assert(withAge.age.equals(Some(20)))

    val withoutAge = new PersonAuxConstructors("Buck Trends")
    assert(withoutAge.name.equals("Buck Trends"))
    assert(withoutAge.age.equals(None))
  }

  test("Address is optional") {
    val withAge = new PersonAuxConstructors("Buck Trends", addr)
    assert(withAge.name.equals("Buck Trends"))
    assert(withAge.address.equals(Some(addr)))

    val withoutAge = new PersonAuxConstructors("Buck Trends")
    assert(withoutAge.name.equals("Buck Trends"))
    assert(withoutAge.address.equals(None))
  }

  test("Age and address can be specified") {
    val p = new PersonAuxConstructors("Buck Trends", 20, addr)
    assert(p.name.equals("Buck Trends"))
    assert(p.age.equals(Some(20)))
    assert(p.address.equals(Some(addr)))
  }
