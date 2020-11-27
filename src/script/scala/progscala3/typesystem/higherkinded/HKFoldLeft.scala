// src/script/scala/progscala3/typesystem/higherkinded/HKFold.scala

object HKFold:
  def apply[T, M[T]](m: M[T])(seed: T)(f: (T, T) => T): T = m match
    case array: Array[T] @unchecked => apply(array.toSeq)(seed)(f)
    case iter: Iterable[T] => iter.foldLeft(seed)(f)
    case opt: Option[T] => opt.foldLeft(seed)(f)

HKFold(List(1, 2, 3))(0)(_+_)
HKFold(Array(1, 2, 3))(0)(_+_)
HKFold(Vector(1 -> 10, 2 -> 20, 3 -> 30))(0 -> 0){
  case ((xs, ys), (x,y)) => (xs+x, ys+y)
}
HKFold(Some(1.1))(0.0)(_+_)
HKFold(None)(0.0)(_+_)
