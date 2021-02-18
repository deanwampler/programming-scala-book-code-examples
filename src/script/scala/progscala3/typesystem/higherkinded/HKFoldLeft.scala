// tag::definitions[]
// src/script/scala/progscala3/typesystem/higherkinded/HKFoldLeft.scala

object HKFoldLeft:       // "HK" for "higher-kinded"

  trait Folder[-M[_]]:                                          // <1>
    def apply[IN, OUT](m: M[IN], seed: OUT, f: (OUT, IN) => OUT): OUT

  given Folder[Iterable] with                                   // <2>
    def apply[IN, OUT](iter: Iterable[IN],
        seed: OUT, f: (OUT, IN) => OUT): OUT =
      var accumulator = seed
      iter.foreach(t => accumulator = f(accumulator, t))
      accumulator

  given Folder[Option] with                                     // <3>
    def apply[IN, OUT](opt: Option[IN],
        seed: OUT, f: (OUT, IN) => OUT): OUT = opt match
      case Some(t) => f(seed, t)
      case None => seed

  def apply[IN, OUT, M[IN]](m: M[IN])(                          // <4>
      seed: OUT)(f: (OUT, IN) => OUT)(using Folder[M]): OUT =
    summon[Folder[M]](m, seed, f)
// end::definitions[]

// tag::usage1[]
import HKFoldLeft.{given, *}

summon[Folder[Iterable]]
summon[Folder[Option]]

HKFoldLeft(List(1, 2, 3))(0)(_+_)
HKFoldLeft(List(1, 2, 3))("(0)")((s, i) => s"($s $i)")
HKFoldLeft(Array(1, 2, 3).toSeq)(0)(_+_)
HKFoldLeft(Array(1, 2, 3).toSeq)("(0)")((s, i) => s"($s $i)")

HKFoldLeft(Vector(1 -> "one", 2 -> "two", 3 -> "three"))(0 -> "(0)"){
  case ((xs, ys), (x,y)) => (xs+x, s"($ys $y)")
}
HKFoldLeft(Some(1.1))(0.0)(_+_)
HKFoldLeft(Option.empty[Int])(0.0)(_+_)

// end::usage1[]

// tag::usage2[]
given Folder[[X] =>> Either[String, X]] with
  def apply[IN, OUT](err: Either[String, IN],
      seed: OUT, f: (OUT, IN) => OUT): OUT = err match
    case Right(t) => f(seed, t)
    case _ => seed

summon[Folder[[X] =>> Either[String, X]]]

val bad: Either[String,Int] = Left("error")
val good: Either[String,Int] = Right(11)
HKFoldLeft(bad)(0.0)(_+_)
HKFoldLeft(good)(2.0)(_+_)
// tag::usage2[]

