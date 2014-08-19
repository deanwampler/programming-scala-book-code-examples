// src/main/scala/progscala2/basicoop/PersonAuxConstructors.sc
import progscala2.basicoop.{Address, Person}

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
// Result: Address(1 Scala Lane,Anytown,CA,98765)

val a2 = new Address("98765")
// Result: Address([unknown],Anytown,CA,98765)

new Person("Buck Trends1")
// Result: Person(Buck Trends1,None,None)

new Person("Buck Trends2", Some(20), Some(a1))
// Result: Person(Buck Trends2,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))


new Person("Buck Trends3", 20, a2)
// Result: Person(Buck Trends3,Some(20),
//           Some(Address([unknown],Anytown,CA,98765)))

new Person("Buck Trends4", 20)
// Result: Person(Buck Trends4,Some(20),None)
