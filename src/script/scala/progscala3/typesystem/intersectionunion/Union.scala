// tag::declarations[]
// src/script/scala/progscala3/typesystem/intersectionunion/Union.scala

case class Bad(message: String)
case class Good(i: Int)

val error = Bad("Failed!")
val result = Good(0)

val seq1 = Seq(error, result)   // Inferred type: Seq[Object]!
val seq: Seq[Good | Bad] = Seq(error, result)
// end::declarations[]

// tag::usage[]
def work(i: Int): Good | Bad =
  if i > 0 then Bad(s"$i must be <= 0") else Good(i)

def process(result: Good | Bad): String = result match
  case Bad(message) => message
  case Good(value) => s"Success! value = $value"

val results: Seq[Good | Bad] = Seq(0, 1).map(work)
val strings = results.map(process)
// end::usage[]

// tag::distributive[]
trait T1
trait T2
trait T3

val x1:  T1 & (T2 | T3)        = new T1 with T2 {}
val x2:  (T1 & T2) | (T1 & T3) = new T1 with T2 {}
val x3:  T1 & (T2 | T3)        = new T1 with T3 {}
val x4:  (T1 & T2) | (T1 & T3) = new T1 with T3 {}
val x5:  T1 & (T2 | T3)        = new T1 with T2 with T3 {}
val x6:  (T1 & T2) | (T1 & T3) = new T1 with T2 with T3 {}

val x7:  T1 | (T2 & T3)        = new T1 {}
val x8:  (T1 | T2) & (T1 | T3) = new T1 {}
val x9:  T1 | (T2 & T3)        = new T2 with T3 {}
val x10: (T1 | T2) & (T1 | T3) = new T2 with T3 {}
val x11: T1 | (T2 & T3)        = new T1 with T2 with T3 {}
val x12: (T1 | T2) & (T1 | T3) = new T1 with T2 with T3 {}
// end::distributive[]

// tag::covariance[]
val t123s: Seq[T1 | T2 | T3] = Seq(new T1 {}, new T2 {}, new T3 {})
val t1s: Seq[T1] = t123s        // ERROR
val t2s: Seq[T2] = t123s        // ERROR
val t3s: Seq[T3] = t123s        // ERROR
// end::covariance[]

// tag::contravariance[]
val t1s: Seq[T1] = Seq(new T1 {})
val t2s: Seq[T2] = Seq(new T2 {})
val t3s: Seq[T3] = Seq(new T3 {})
val t123s1: Seq[T1 | T2 | T3] = t1s
val t123s2: Seq[T1 | T2 | T3] = t2s
val t123s3: Seq[T1 | T2 | T3] = t3s
// end::contravariance[]

// tag::contravariantfunctions[]
val f123a: (T1 | T2 | T3) => String = _ match
  case t1: T1 => "T1"
  case t2: T2 => "T2"
  case t3: T3 => "T3"
val f123b: (T1 => String) & (T2 => String) & (T3 => String) = f123a
val t123s: Seq[T1 | T2 | T3] = Seq(new T1 {}, new T2 {}, new T3 {})
t123s.map(f123a)
t123s.map(f123b)
// tag::contravariantfunctions[]
