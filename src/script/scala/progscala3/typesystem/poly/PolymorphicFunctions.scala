// tag::first[]
// src/script/scala/progscala3/typesystem/poly/PolymorphicFunctions.scala

val seq = Seq(1,2,3,4,5)

def m1[A <: AnyVal](seq: Seq[A]) = seq.map(e => (e,e))          // <1>
val pf1 = [A <: AnyVal] => (seq: Seq[A]) => seq.map(e => (e,e)) // <2>
val pf2: [A <: AnyVal] => Seq[A] => Seq[(A,A)] =                // <3>
  [A] => (seq: Seq[A]) => seq.map(e => (e,e))
m1(seq)    // List((1,1), (2,2), (3,3), (4,4), (5,5))
pf1(seq)   // same
pf2(seq)   // same
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
// This section doesn't appear in the book.
// The first syntax here may be the first supported in a subsequent Scala 3 release.
val pf2a = [A] => (seq: Seq[A]) => (using n: Numeric[A]) =>
  seq.reduce((a,b) => n.times(a,b))
val pf2b = [T] => (seq: Seq[T]) => (using Numeric[T]) => // anonymous disallowed
  seq.reduce((a,b) => summon.times(a,b))
val pf2c = [T : Numeric] => (seq: Seq[T]) =>  // context bounds disallowed
  seq.reduce((a,b) => summon.times(a,b))
val pf23 = [T] => (seq: Seq[T])(using n: Numeric[T]) =>  // need the => between arg lists.
  seq.reduce((a,b) => summon.times(a,b))
// end::doesnotwork[]

// tag::twoparameters[]
// This section doesn't appear in the book.
// We've had parameterized methods for a long time:
def toMap[K,V](key: K)(value: V): Map[K,V] = Map(key -> value)
toMap("one")(1.1)
toMap(2L)("two")

// Now functions can have type parameters. Note the [K,V] => in BOTH the bodies
// and the type signature for toMapF1good
val toMapF1good = [K,V] => (key: K) => (value: V) => Map(key -> value) // good
val toMapF2good: [K,V] => K => V => Map[K,V] =
  [K,V] => (key: K) => (value: V) => Map(key -> value) // good

// But "currying" the type parameters isn't supported:
val toMapF1b = [K] => [V] => (key: K) => (value: V) => Map(key -> value) // bad
val toMapF2b: [K] => [V] => K => V => Map[K,V] =
  [K] => [V] => (key: K) => (value: V) => Map(key -> value) // bad
// end::twoparameters[]
