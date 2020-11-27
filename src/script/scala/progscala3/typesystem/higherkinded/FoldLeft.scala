// tag::definitions[]
// src/script/scala/progscala3/typesystem/higherkinded/FoldLeft.scala

object FoldLeft:
  def apply[T](seq: Seq[T])(seed: T)(f: (T, T) => T): T =
    var accumulator = seed
    seq.foreach(t => accumulator = f(accumulator, t))
    accumulator

  def apply[T](opt: Option[T])(seed: T)(f: (T, T) => T): T = opt match
    case Some(t) => f(seed, t)
    case None => seed
// end::definitions[]

FoldLeft(List(1, 2, 3))(0)(_+_)
FoldLeft(Array(1, 2, 3).toSeq)(0)(_+_)                     // <1>
FoldLeft(Vector(1 -> 10, 2 -> 20, 3 -> 30))(0 -> 0){
  case ((xs, ys), (x,y)) => (xs+x, ys+y)
}
FoldLeft(Some(1.1))(0.0)(_+_)
FoldLeft(None)(0.0)(_+_)
