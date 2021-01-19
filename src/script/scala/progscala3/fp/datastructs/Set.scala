// src/script/scala/progscala3/fp/datastructs/Set.scala

val states = Set("Alabama", "Alaska", "Wyoming")

val lengths = states.map (st => st.length)            // <1>
assert(lengths == Set(6, 7))  // Two names are 7 characters long

val states2 = states + "Virginia"
assert(states2 == Set("Alabama", "Alaska", "Wyoming", "Virginia"))

val states3 = states ++ Seq("New York", "Illinois", "Alaska")  // <1>
assert(states3 ==  Set("Alabama", "Alaska", "Wyoming", "New York", "Illinois"))
