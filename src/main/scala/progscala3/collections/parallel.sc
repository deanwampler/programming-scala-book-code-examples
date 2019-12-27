// src/main/scala/progscala3/collections/parallel.sc

val f1 = ((1 to 10) fold "") ((s1, s2) => s"$s1 - $s2")
println(f1)
// Result: " - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10"

val f2 = ((1 to 10).par fold "") ((s1, s2) => s"$s1 - $s2")
println(f2)
// Results for two runs:
// " - 1 -  - 2 -  - 3 - 4 - 5 -  - 6 -  - 7 -  - 8 -  - 9 -  - 10"
// " - 1 -  - 2 -  - 3 -  - 4 - 5 -  - 6 -  - 7 -  - 8 - 9 - 10"

val f3 = ((1 to 10) fold 0) ((s1, s2) => s1 + s2)
println(f3)
// Result: 55

val f4 = ((1 to 10).par fold 0) ((s1, s2) => s1 + s2)
println(f4)
// Result: 55
