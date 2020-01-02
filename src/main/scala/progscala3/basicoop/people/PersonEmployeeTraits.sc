// src/main/scala/progscala3/basicoop/people/PersonEmployeeTraits.sc
import progscala3.basicoop.people.{
  Address, EmployeeWithTraits, PersonWithTraits, ZipCode }

val ceoAddress  = Address("1 Scala Lane", "Bay Area", "CA", ZipCode(98765))
println(ceoAddress)

val buckAddress = Address("19 Java Lane", "Bay Area", "CA", ZipCode(98765))
println(buckAddress)

val ceo = EmployeeWithTraits(
  name = "Thaddeus Biggles III", title = "CEO", age = Some(50),
  address = Some(ceoAddress), manager = None)
assert(ceo.name    == "Thaddeus Biggles III")
assert(ceo.title   == "CEO")
assert(ceo.age     == Some(50))
assert(ceo.address == Some(ceoAddress))
assert(ceo.manager == None)

val ceoSpouse = PersonWithTraits(
  name = "Octavia Biggles", 
  address = Some(ceoAddress))
assert(ceoSpouse.name    == "Octavia Biggles")
assert(ceoSpouse.age     == None)
assert(ceoSpouse.address == Some(ceoAddress))

val buck = EmployeeWithTraits(
  name = "Buck Trends", title = "Zombie Dev", age = Some(20),
  address = Some(buckAddress), manager = Some(ceo))
assert(buck.name    == "Buck Trends")
assert(buck.title   == "Zombie Dev")
assert(buck.age     == Some(20))
assert(buck.address == Some(buckAddress))
assert(buck.manager == Some(ceo))
