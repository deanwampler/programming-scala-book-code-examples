// tag::first[]
// src/script/scala/progscala3/typesystem/poly/PolymorphicFunctions.scala

val seq = Seq(1,2,3,4,5)

def m1[A <: AnyVal](seq: Seq[A]) = seq.map(e => (e,e))          // <1>
val pf1 = [A <: AnyVal] => (seq: Seq[A]) => seq.map(e => (e,e)) // <2>
m1(seq), pf1(seq)   // both == List(1, 2, 3, 4, 5)
// end::first[]

// tag::second[]
def m2[A : Numeric](seq: Seq[A]): A =
  seq.reduce((a,b) => summon.times(a,b))
val pf2 = [A] => (seq: Seq[A]) => (using n: Numeric[A]) =>
  seq.reduce((a,b) => n.times(a,b))
m2(seq), pf2(seq)   // both == 120
// end::second[]

// tag::third[]
def work[A <: AnyRef](x:A)(f: [A <: AnyRef] => A => String) = s"<${f(x)}>"
val fas = [A <: AnyRef] => (a: A) => s"$a-$a"
work(1)(fas)
work(2.2)(fas)
// end::third[]

// tag::doesnotwork[]
val pf2a = [T : Numeric] => (seq: Seq[T]) =>  // context bounds disallowed
  seq.reduce((a,b) => summon.times(a,b))
val pf2b = [T] => (seq: Seq[T]) => (using Numeric[T]) => // anonymous disallowed
  seq.reduce((a,b) => summon.times(a,b))
val pf2c = [T] => (seq: Seq[T])(using n: Numeric[T]) =>  // need the =>
  seq.reduce((a,b) => summon.times(a,b))
// end::doesnotwork[]
