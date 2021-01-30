// tag::first[]
// src/script/scala/progscala3/typesystem/poly/PolymorphicFunctions.scala

val seq = Seq(1,2,3,4,5)

def m1[A <: AnyVal](seq: Seq[A]) = seq.map(e => (e,e))          // <1>
val pf1 = [A <: AnyVal] => (seq: Seq[A]) => seq.map(e => (e,e)) // <2>
m1(seq)
pf1(seq)   // both == List(1, 2, 3, 4, 5)
// end::first[]

// tag::second[]
def m2[A : Numeric](seq: Seq[A]): A =                           // Okay
  seq.reduce((a,b) => summon.times(a,b))
val pf2 = [A] => (seq: Seq[A]) => (using n: Numeric[A]) =>      // ERROR
  seq.reduce((a,b) => n.times(a,b))
val pf2 = [A : Numeric] => (seq: Seq[A]) =>                     // ERROR
  seq.reduce((a,b) => n.times(a,b))
// end::second[]

// tag::third[]
trait Base:
  def id: String
case object O1 extends Base:
  def id: String = "object O1"
case object O2 extends Base:
  def id: String = "object O2"

def work[B <: Base](b: B)(f: [B <: Base] => B => String) = s"<${f(b)}>"
val fas = [B <: Base] => (b: B) => s"found: $b"
work(O1)(fas)      // Returns: "<found: O1>"
work(O2)(fas)      // Returns: "<found: O2>"
// end::third[]

// tag::doesnotwork[]
// The first syntax here may be the first supported in a Scala 3 release.
val pf2a = [A] => (seq: Seq[A]) => (using n: Numeric[A]) =>
  seq.reduce((a,b) => n.times(a,b))
val pf2b = [T] => (seq: Seq[T]) => (using Numeric[T]) => // anonymous disallowed
  seq.reduce((a,b) => summon.times(a,b))
val pf2c = [T : Numeric] => (seq: Seq[T]) =>  // context bounds disallowed
  seq.reduce((a,b) => summon.times(a,b))
val pf23 = [T] => (seq: Seq[T])(using n: Numeric[T]) =>  // need the =>
  seq.reduce((a,b) => summon.times(a,b))
// end::doesnotwork[]
