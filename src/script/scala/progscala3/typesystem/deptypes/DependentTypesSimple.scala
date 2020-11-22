// tag::singleton[]
// src/script/scala/progscala3/typesystem/deptypes/DependentTypesSimple.scala

val one: 1 = 1
val two: 2.2 = 2.2
val bad: 2.2 = 2.21
// end::singleton[]

// tag::subtypes[]
summon[1 <:< Int]
summon[2.2 <:< Double]
summon[2 <:< Double]
// end::subtypes[]

// tag::opsint[]
import scala.compiletime.ops.int._                              // <1>

val one2: 0 + 1 = 0 + 1
val two2: 1 + 1 = 1 + 1
val three2: 1 + 2 = 1 + 2
val five: 3 * 2 - 1 = 3 * 2 - 1
// end::opsint[]

// tag::s[]
import scala.compiletime.S

val one3: S[0] = 1                                              // <1>
val two3a: S[S[0]] = 2                                          // <2>
val two3b: S[1] = 2                                             // <3>
val three3a: S[S[S[0]]] = 3
val three3b: S[2] = 3
// end::s[]

// tag::opsstring[]
import scala.compiletime.ops.string._

val s1: "ab" + "cd" = "abcd"
val bad2: "ab" + "cd" = "abcdef"
// end::opsstring[]

// tag::opsboolean[]
import scala.compiletime.ops.boolean._

val tt1: true  && true  = true
val tf1: true  && false = false
val ft1: false && true  = false
val ff1: false && false = false
val tt2: true  || true  = true
val tf2: true  || false = true
val ft2: false || true  = true
val ff2: false || false = false
// end::opsboolean[]

// tag::opsany[]
import scala.compiletime.ops.any._

1+1 == 2                // All four return true
1+1+1 != 2
Int == Int
Int != (Int, Double)
// end::opsany[]
