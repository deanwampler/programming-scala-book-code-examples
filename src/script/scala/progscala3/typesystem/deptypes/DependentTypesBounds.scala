// tag::dtlist[]
// src/script/scala/progscala3/typesystem/deptypes/DependentTypesBounds.scala

// This example is not in the book, but it shows a few other tricks with
// dependent types. This file focuses on extensions to NSize[N] which always
// allows values equal to N.

import scala.compiletime.S
import scala.compiletime.ops.int._

// NSize[N] from DependentTypes.scala
type NSize[N] <: Int = N match
  case 0 => 0
  case S[m] => S[NSize[m]]

// A type with allowed values between 0 and N-1, inclusive, the same numbers
// for indexing into a sequence of size N!
type IndexOf[N] <: Int = N match
  case 1 => 0
  case S[m] => NSize[m] | IndexOf[m]

// A type that allows values between MIN and MAX. IndexOf could be written
// with this one, as it generalizes it.
// Curiously, the bound <: Int is not required on MAX, only MIN, because
// we match on MAX, but just use MIN, so MAX must have an "implicit" <: Int
// bound, while we have to specify it explicitly for MIN.
type Bounded[MIN <: Int, MAX] <: Int = MAX match
  case MIN  => MIN
  case S[m] => NSize[MAX] | Bounded[MIN, m]

val n00: NSize[0] = 0
val n01: NSize[0] = 1         // ERROR
val n11: NSize[1] = 1

val im1: IndexOf[3] = -1      // ERROR
val i0: IndexOf[3]  = 0
val i1: IndexOf[3]  = 1
val i2: IndexOf[3]  = 2
val i3: IndexOf[3]  = 3       // ERROR

val b3: Bounded[4,6] = 3      // ERROR
val b4: Bounded[4,6] = 4
val b5: Bounded[4,6] = 5
val b6: Bounded[4,6] = 6
val b7: Bounded[4,6] = 7      // ERROR

type IndexOfBounded[N <: Int] = Bounded[0,N-1]

val im1: IndexOfBounded[3] = -1      // ERROR
val i0: IndexOfBounded[3]  = 0
val i1: IndexOfBounded[3]  = 1
val i2: IndexOfBounded[3]  = 2
val i3: IndexOfBounded[3]  = 3       // ERROR

