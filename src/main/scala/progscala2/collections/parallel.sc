// src/main/scala/progscala2/collections/parallel.sc

((1 to 10) fold "") ((s1, s2) => s"$s1 - $s2")
// Result: " - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10"

((1 to 10).par fold "") ((s1, s2) => s"$s1 - $s2")
// Results for two runs:
// " - 1 -  - 2 -  - 3 - 4 - 5 -  - 6 -  - 7 -  - 8 -  - 9 -  - 10"
// " - 1 -  - 2 -  - 3 -  - 4 - 5 -  - 6 -  - 7 -  - 8 - 9 - 10"

((1 to 10) fold 0) ((s1, s2) => s1 + s2)
// Result: 55

((1 to 10).par fold 0) ((s1, s2) => s1 + s2)
// Result: 55
