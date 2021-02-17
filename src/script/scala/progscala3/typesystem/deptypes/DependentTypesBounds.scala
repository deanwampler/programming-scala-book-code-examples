// tag::dtlist[]
// src/script/scala/progscala3/typesystem/deptypes/DependentTypesBounds.scala

// This example is not in the book, but it shows a few other tricks with
// dependent types. This file focuses on recursive match types for integer
// ranges.

import scala.compiletime.S
import scala.compiletime.ops.int.*

// A type that allows values between MIN and MAX, inclusive.
type Bounded[MIN <: Int, MAX <: Int] <: Int = MAX match
  case MIN  => MIN
  case S[m] => MAX | Bounded[MIN, m]

val b3: Bounded[4,6] = 3      // ERROR
val b4: Bounded[4,6] = 4
val b5: Bounded[4,6] = 5
val b6: Bounded[4,6] = 6
val b7: Bounded[4,6] = 7      // ERROR

// A type with allowed values between 0 and N-1, inclusive, the same numbers
// for indexing into a sequence of size N!
type IndexOf[N] = Bounded[0,N-1]

val im1: IndexOf[3] = -1      // ERROR
val i0:  IndexOf[3]  = 0
val i1:  IndexOf[3]  = 1
val i2:  IndexOf[3]  = 2
val i3:  IndexOf[3]  = 3       // ERROR

