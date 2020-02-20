// src/main/scala/progscala3/basicoop/people/PersonEmployeeTraitsSuite.scala
package progscala3.basicoop.people

import munit._
import progscala3.metaprogramming.requirement

class PersonEmployeeTraitsSuite extends FunSuite {

	val ceoAddress  = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))
	val buckAddress = Address("19 Java Lane", "Bay Area", "CA", ZipCode(98765))

	test("EmployeeWithTraits combines PersonState with EmployeeState") {
		val ceo = EmployeeWithTraits(
	  	name = "Thaddeus Biggles III", title = "CEO", age = Some(50),
	  	address = Some(ceoAddress), manager = None)
		assert(ceo.name    == "Thaddeus Biggles III")
		assert(ceo.title   == "CEO")
		assert(ceo.age     == Some(50))
		assert(ceo.address == Some(ceoAddress))
		assert(ceo.manager == None)
	}

	test("PersonWithTraits adds fields to PersonState") {
		val ceoSpouse = PersonWithTraits(
		  name = "Octavia Biggles",
		  address = Some(ceoAddress))
		assert(ceoSpouse.name    == "Octavia Biggles")
		assert(ceoSpouse.age     == None)
		assert(ceoSpouse.address == Some(ceoAddress))
	}
}
