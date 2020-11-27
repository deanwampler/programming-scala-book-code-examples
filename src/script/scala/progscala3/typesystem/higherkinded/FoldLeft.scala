// src/script/scala/progscala3/typesystem/higherkinded/Fold.scala

object Fold:
  def apply[T](seq: Seq[T])(seed: T)(f: (T, T) => T): T = seq.foldLeft(seed)(f)
  def apply[T](opt: Option[T])(seed: T)(f: (T, T) => T): T = opt.foldLeft(seed)(f)

Fold(List(1, 2, 3))(0)(_+_)
Fold(Array(1, 2, 3).toSeq)(0)(_+_)
Fold(Vector(1 -> 10, 2 -> 20, 3 -> 30))(0 -> 0){
  case ((xs, ys), (x,y)) => (xs+x, ys+y)
}
Fold(Some(1.1))(0.0)(_+_)
Fold(None)(0.0)(_+_)
