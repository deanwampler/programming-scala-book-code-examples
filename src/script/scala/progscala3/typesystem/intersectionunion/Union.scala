// tag::declarations[]
// src/script/scala/progscala3/typesystem/intersectionunion/Union.scala
// NOTE: I've reworked parts of this script to fix some errors and ambiguities.

case class Bad(message: String)
case class Good(i: Int)

val error = Bad("Failed!")
val result = Good(0)

val seq1 = Seq(error, result)   // Inferred type: Seq[AnyRef] or Seq[Object]!
val seq: Seq[Good | Bad] = Seq(error, result)
// end::declarations[]

// tag::usage[]
def work(i: Int): Good | Bad =
  if i > 0 then Bad(s"$i must be <= 0") else Good(i)

def process(result: Good | Bad): String = result match
  case Bad(message) => message
  case Good(value) => s"Success! value = $value"

val results = Seq(0, 1).map(work)
val strings = results.map(process)
// end::usage[]

// tag::distributive[]
trait A; trait B; trait C

summon[(A & (B | C)) =:= ((A & B) | (A & C))]
summon[(A | (B & C)) =:= ((A | B) & (A | C))]

val x1:  A & (B | C)       = new A with B {}
val x2:  A & (B | C)       = new A with C {}
val x3:  A & (B | C)       = new A with B with C {}
val x4:  (A & B) | (A & C) = new A with B {}
val x5:  (A & B) | (A & C) = new A with C {}
val x6:  (A & B) | (A & C) = new A with B with C {}

val x7:  A | (B & C)       = new A {}
val x8:  A | (B & C)       = new B with C {}
val x9:  A | (B & C)       = new A with B with C {}
val x10: (A | B) & (A | C) = new A {}
val x11: (A | B) & (A | C) = new B with C {}
val x12: (A | B) & (A | C) = new A with B with C {}
// end::distributive[]

// WARNING: I got this backwards in the book! The behavior IS covariant.
// tag::covariance[]
// First, note that A is a subtype of A|B|C and Similarly for B and C:
val a: A = new A {}
val b: B = new B {}
val c: C = new C {}

val abc1: A | B | C = a
val abc2: A | B | C = b
val abc3: A | B | C = c

val bca1: C | B | A = a  // commutative!
val bca2: C | B | A = b
val bca3: C | B | A = c
val cba1: B | C | A = a
val cba2: B | C | A = b
val cba3: B | C | A = c  // etc.

val seqAs: Seq[A] = Seq(a)
val seqBs: Seq[B] = Seq(b)
val seqCs: Seq[C] = Seq(c)
val seqABCs1: Seq[A | B | C] = seqAs
val seqABCs2: Seq[A | B | C] = seqBs
val seqABCs3: Seq[A | B | C] = seqCs
// end::covariance[]

// tag::contravariance[]
val tABCs: Seq[A | B | C] = Seq(a, b, c)
val tAs: Seq[A] = tABCs        // ERROR
val tBs: Seq[B] = tABCs        // ERROR
val tCs: Seq[C] = tABCs        // ERROR
// end::contravariance[]

// tag::contravariantfunctions[]
val fABC1: (A | B | C) => String = _ match
  case t1: A => "A"
  case t2: B => "B"
  case t3: C => "C"
val fABC2: (A => String) & (B => String) & (C => String) = fABC1

val seqABCs: Seq[A | B | C] = Seq(a, b, c)
seqABCs.map(fABC1)
seqABCs.map(fABC2)
seqABCs.map((x: AnyRef) => s"<$x>")  // Not as nice output!!
seqABCs.map(_.toString)              // similar

// Note that we can pass fABC1 or fABC2 for a function A => String. It works similarly for B and C.
def stringizeA(a: A)(f: A => String): String = f(a)
stringizeA(a)(fABC1)
stringizeA(a)(fABC2)
// end::contravariantfunctions[]
