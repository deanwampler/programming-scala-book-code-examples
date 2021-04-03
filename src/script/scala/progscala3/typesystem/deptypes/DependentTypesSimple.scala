// tag::singleton[]
// src/script/scala/progscala3/typesystem/deptypes/DependentTypesSimple.scala

val one: 1 = 1
val two: 2.2 = 2.2
val bad: 2.2 = 2.21     // ERROR
// end::singleton[]

// tag::subtypes[]
summon[1 <:< Int]
summon[2.2 <:< Double]
summon[2 <:< Double]    // "Cannot prove that (2 : Int) <:< Double"
// end::subtypes[]

// tag::opsany[]
def opsAny =                                                    // <1>
  import scala.compiletime.ops.any.*

  val any1: 2 == 2      = true                                  // <2>
  val any2: 1 == 2      = false
  val any3: 1 != 2      = true
  val any4: "" == ""    = true
  val any5: "" != ""    = false
  val any6: "" != "boo" = true

  valueOf[2 == 2]  == true                                      // <3>
  valueOf[1 == 2]  == false
  valueOf[1 != 2]  == true

opsAny                                                          // <4>
// end::opsany[]

// tag::opsint[]
def opsInt =
  import scala.compiletime.ops.int.*                            // <1>

  val i1: 0 + 1     = 0 + 1
  val i2: 1 + 1     = 1 + 1
  val i3: 1 + 2     = 1 + 2
  val i4: 3 * 2 - 1 = 3 * 2 - 1
  val i5: 12 / 3    = 4
  val i6: 11 % 4    = 3

  val lshift:  1 <<  2 = 4                                      // <2>
  val rshift:  8 >>  2 = 2
  val rshift2: 8 >>> 2 = 2                                      // <3>

  val b2: 1 <  2 = true                                         // <4>
  val b3: 1 <= 2 = true
  val b4: 2 <  1 = false
  val b5: 1 >  2 = false
  val b6: 1 >= 2 = false
  val b7: 2 >  1 = true

  val xor: 14 ^ 5           = 11  // 14 xor 5 => 1110 ^ 0101 => 1011 => 11
  val and: BitwiseAnd[5, 4] = 4   // 5 & 4 => 101 & 100 == 100 => 4
  val or:  BitwiseOr[5, 3]  = 7   // 5 | 3 => 101 | 011 == 111 => 7

  val abs: Abs[-1] = 1
  val neg: Negate[2] = -2
  val min: Min[3, 5] = 3
  val max: Max[3, 5] = 5
  val s:   ToString[123] = "123"

opsInt
// end::opsint[]

// tag::s[]
def tryS =
  import scala.compiletime.ops.int.S

  val s1:  S[0] = 1                                             // <1>
  val s2a: S[S[0]] = 2                                          // <2>
  val s2b: S[1] = 2                                             // <3>
  val s3a: S[S[S[0]]] = 3
  val s3b: S[2] = 3

tryS
// end::s[]

// tag::opsboolean[]
def opsBoolean =
  import scala.compiletime.ops.boolean.*

  val t1: true = true
  val f1: ![true] = false                                       // <1>
  val tt1: true  && true  = true
  val tf1: true  && false = false
  val ft1: false && true  = false
  val ff1: false && false = false
  val tt2: true  || true  = true
  val tf2: true  || false = true
  val ft2: false || true  = true
  val ff2: false || false = false
  val tt3: true   ^ true  = false                               // <2>
  val tf3: true   ^ false = true
  val ft3: false  ^ true  = true
  val ff3: false  ^ false = false

opsBoolean
// end::opsboolean[]

// tag::opsstring[]
def opsString =
  import scala.compiletime.ops.string.*

  val s1: "ab" + "cd" = "abcd"
  val bad2: "ab" + "cd" = "abcdef"  // ERROR

opsString
// end::opsstring[]
