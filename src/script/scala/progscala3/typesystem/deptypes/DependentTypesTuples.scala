// tag::dtlist[]
// src/script/scala/progscala3/typesystem/deptypes/DependentTypesTuples.scala

// This example is not in the book, but it shows a few other tricks with
// dependent types. This file focuses on tuples. The book does discuss how you
// can manipulate tuples like lists. Here, we'll experiment with dependent
// types and the allowed arities (sizes) of the tuples.

import scala.compiletime.S
import scala.compiletime.ops.int.*

// Only allow tuple values with exactly N elements.
type FixedLength[N <: Int, A] = N match
  case 0 => EmptyTuple
  case S[m] => A *: FixedLength[m, A]

// Allow tuple values with 0 to N elements.
type MaxLength[N <: Int, A] = N match
  case 0 => EmptyTuple
  case S[m] => FixedLength[N, A] | MaxLength[m, A]

val f2: FixedLength[3, Double] = (1.1, 2.2)            // ERROR!
val f3: FixedLength[3, Double] = (1.1, 2.2, 3.3)       // Okay
val f4: FixedLength[3, Double] = (1.1, 2.2, 3.3, 4.4)  // ERROR!

val l0: MaxLength[3, Int] = EmptyTuple      // Can't use ()!
val l1: MaxLength[3, Int] = 1 *: EmptyTuple // (1) parses to 1: Int!!!
val l2: MaxLength[3, Int] = (1, 2)
val l3: MaxLength[3, Int] = (1, 2, 3)
val l4: MaxLength[3, Int] = (1, 2, 3, 4)    // ERROR!

// Another way of writing the match types. Note the second case clauses.
// Also notice the slightly different type signatures reported compared to
// the examples above.

type FixedLength2[N <: Int, A] = N match
  case 0 => EmptyTuple
  case ? => A *: FixedLength2[N - 1, A]

type MaxLength2[N <: Int, A] = N match
  case 0 => EmptyTuple
  case ? => FixedLength2[N, A] | MaxLength[N - 1, A]

val f3b: FixedLength2[3, Double] = (1.1, 2.2, 3.3)
val l2b: MaxLength2[3, Int] = (1, 2)

// end::simplelist[]
