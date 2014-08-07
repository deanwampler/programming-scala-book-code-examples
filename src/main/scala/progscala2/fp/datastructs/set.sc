// src/main/scala/progscala2/fp/datastructs/set.sc

val states = Set("Alabama", "Alaska", "Wyoming")

val lengths = states map (st => st.length)
println(lengths)

val states2 = states + "Virginia"
println(states2)

val states3 = states2 + ("New York", "Illinois")
println(states3)
