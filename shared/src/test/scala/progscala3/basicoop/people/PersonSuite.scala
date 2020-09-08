// src/test/scala/progscala3/basicoop/people/PersonSuite.scala
package progscala3.basicoop.people

import munit._

class PersonSuite extends FunSuite:

  val addr = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))

  test("Person.toString returns a human-readable string") {
    assert(addr.toString == "Address(1 Scala Lane,Bay Area,CA,98765)")
  }

  test("Age is optional") {
    val withAge = Person("Buck Trends", age = 20)
    assert(withAge.name.equals("Buck Trends"))
    assert(withAge.age.equals(Some(20)))

    val withoutAge = Person("Buck Trends")
    assert(withoutAge.name.equals("Buck Trends"))
    assert(withoutAge.age.equals(None))
  }

  test("Address is optional") {
    val withAge = Person("Buck Trends", address = addr)
    assert(withAge.name.equals("Buck Trends"))
    assert(withAge.address.equals(Some(addr)))

    val withoutAge = Person("Buck Trends")
    assert(withoutAge.name.equals("Buck Trends"))
    assert(withoutAge.address.equals(None))
  }

  test("Age and address can be specified") {
    val p1 = Person("Buck Trends", Some(20), Some(addr))
    assert(p1.name.equals("Buck Trends"))
    assert(p1.age.equals(Some(20)))
    assert(p1.address.equals(Some(addr)))

    val p2 = Person("Buck Trends", 20, addr)
    assert(p2.name.equals("Buck Trends"))
    assert(p2.age.equals(Some(20)))
    assert(p2.address.equals(Some(addr)))
  }
