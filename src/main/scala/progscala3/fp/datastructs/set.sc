// src/main/scala/progscala3/fp/datastructs/set.sc

val states = Set("Alabama", "Alaska", "Wyoming")

val lengths = states map (st => st.length)
assert(lengths == Set(6, 7))  // two names are 7 characters long

val states2 = states + "Virginia"
assert(states2 == Set("Alabama", "Alaska", "Wyoming", "Virginia"))

val states3 = states2 ++ Seq("New York", "Illinois", "Alaska")  // <1>
assert(states3 == 
  Set("Alabama", "Alaska", "Wyoming", "Virginia", "New York", "Illinois"))
