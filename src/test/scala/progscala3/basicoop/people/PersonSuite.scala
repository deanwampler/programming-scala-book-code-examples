// src/test/scala/progscala3/basicoop/people/PersonSuite.scala
package progscala3.basicoop.people

import munit.*

class PersonSuite extends FunSuite:

  val addr = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))

  test("Person.toString returns a human-readable string") {
    assert(addr.toString == "Address(1 Scala Lane,Bay Area,CA,98765)")
  }

  test("Age is optional") {
    val withAge = Person("Buck Trends", age = 20)
    assert(withAge.name == "Buck Trends")
    assert(withAge.age == Some(20))

    val withoutAge = Person("Buck Trends")
    assert(withoutAge.name == "Buck Trends")
    assert(withoutAge.age == None)
  }

  test("Address is optional") {
    val withAge = Person("Buck Trends", address = addr)
    assert(withAge.name == "Buck Trends")
    assert(withAge.address == Some(addr))

    val withoutAge = Person("Buck Trends")
    assert(withoutAge.name == "Buck Trends")
    assert(withoutAge.address == None)
  }

  test("Age and address can be specified") {
    val p1 = Person("Buck Trends", Some(20), Some(addr))
    assert(p1.name == "Buck Trends")
    assert(p1.age == Some(20))
    assert(p1.address == Some(addr))

    val p2 = Person("Buck Trends", 20, addr)
    assert(p2.name == "Buck Trends")
    assert(p2.age == Some(20))
    assert(p2.address == Some(addr))
  }
