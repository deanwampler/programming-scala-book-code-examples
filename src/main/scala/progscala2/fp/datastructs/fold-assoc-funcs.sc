// src/main/scala/progscala2/fp/datastructs/fold-assoc-funcs.sc

// fac: a func. that is associative and commutative.
// Define left and right versions, because reduceLeft takes the accumulator
// as the first argument, while reduceRight takes it as the second arg.
val facLeft  = (accum: Int, x: Int) => accum + x
val facRight = (x: Int, accum: Int) => accum + x

val list1 = List(1,2,3,4,5)

list1 reduceLeft  facLeft   // = 15
list1 reduceRight facRight  // = 15

// fnc: a func. that is associative but not commutative:
val fncLeft  = (accum: Int, x: Int) => accum - x
val fncRight = (x: Int, accum: Int) => accum - x

list1 reduceLeft  fncLeft   // = -13
list1 reduceRight fncRight  // = -5

// reduceLeft was this:
((((1 - 2) - 3) - 4) - 5)      // = -13
// reduceRight was this:
((((5 - 4) - 3) - 2) - 1)      // = -5
// or put another way, with the numbers in their original order:
(-1 + (-2 + (-3 + (-4 + 5))))  // = -5

// But "x - y" is associative if we note that "x - y" == "x + -y":
((((1 - 2) - 3) - 4) - 5)      // = -13
((((1 + -2) + -3) + -4) + -5)  // = -13
(1 + (-2 + (-3 + (-4 + -5))))  // = -13

// fnac: a func. that is neither associative nor commutative.
val fnacLeft  = (x: String, y: String) => s"($x)-($y)"
val fnacRight = (x: String, y: String) => s"($y)-($x)"

val list2 = list1 map (_.toString)

list2 reduceLeft  fnacLeft   // = ((((1)-(2))-(3))-(4))-(5)
list2 reduceRight fnacRight  // = ((((5)-(4))-(3))-(2))-(1)
list2 reduceRight fnacLeft   // = (1)-((2)-((3)-((4)-(5))))

