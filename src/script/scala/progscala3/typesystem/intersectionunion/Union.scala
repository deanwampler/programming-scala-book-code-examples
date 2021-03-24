// tag::declarations[]
// src/script/scala/progscala3/typesystem/intersectionunion/Union.scala

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

// tag::covariance[]
val tABCs: Seq[A | B | C] = Seq(new A {}, new B {}, new C {})
val tAs: Seq[A] = tABCs        // ERROR
val tBs: Seq[B] = tABCs        // ERROR
val tCs: Seq[C] = tABCs        // ERROR
// end::covariance[]

// tag::contravariance[]
val seqAs: Seq[A] = Seq(new A {})
val seqBs: Seq[B] = Seq(new B {})
val seqCs: Seq[C] = Seq(new C {})
val seqABCs1: Seq[A | B | C] = seqAs
val seqABCs2: Seq[A | B | C] = seqBs
val seqABCs3: Seq[A | B | C] = seqCs
// end::contravariance[]

// tag::contravariantfunctions[]
val fABC1: (A | B | C) => String = _ match
  case t1: A => "A"
  case t2: B => "B"
  case t3: C => "C"
val fABC2: (A => String) & (B => String) & (C => String) = fABC1

val seqABCs: Seq[A | B | C] = Seq(new A {}, new B {}, new C {})
seqABCs.map(fABC1)
seqABCs.map(fABC2)
seqABCs.map((x: AnyRef) => s"<$x>")
// end::contravariantfunctions[]
