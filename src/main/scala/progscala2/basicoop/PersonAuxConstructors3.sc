// src/main/scala/progscala2/basicoop/PersonAuxConstructors3.sc
import progscala2.basicoop.Address
import progscala2.basicoop3.Person3

val a1 = new Address("1 Scala Lane", "Anytown", "CA", "98765")
val a2 = new Address("98765")

Person3("Buck Trends1")                                    // Primary
// Result: Person3(Buck Trends1,None,None)

Person3("Buck Trends2", Some(20), Some(a1))                // Primary
// Result: Person3(Buck Trends2,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))

Person3("Buck Trends3", 20, a1)
// Result: Person3(Buck Trends3,Some(20),
//           Some(Address(1 Scala Lane,Anytown,CA,98765)))

Person3("Buck Trends4", Some(20))                          // Primary
// Result: Person3(Buck Trends4,Some(20),None)

Person3("Buck Trends5", 20)
// Result: Person3(Buck Trends5,Some(20),None)

Person3("Buck Trends6", address = Some(a2))                // Primary
// Result: Person3(Buck Trends6,None,
//           Some(Address([unknown],Anytown,CA,98765)))

Person3("Buck Trends7", address = a2)
// Result: Person3(Buck Trends7,None,
//           Some(Address([unknown],Anytown,CA,98765)))
