// tag::definitions[]
// src/script/scala/progscala3/typesystem/higherkinded/HKFoldLeft.scala

object HKFoldLeft:       // "HK" for "higher-kinded"
  def apply[T, M[T]](m: M[T])(seed: T)(f: (T, T) => T): T = m match  // <1>
    case iter: Iterable[T] =>                                        // <2>
      var accumulator = seed
      iter.foreach(t => accumulator = f(accumulator, t))
      accumulator
    case array: Array[T] @unchecked => apply(array.toSeq)(seed)(f)
    case opt: Option[T] => opt match
      case Some(t) => f(seed, t)
      case None => seed
// end::definitions[]

HKFoldLeft(List(1, 2, 3))(0)(_+_)
HKFoldLeft(Array(1, 2, 3))(0)(_+_)      // toSeq not required
HKFoldLeft(Vector(1 -> 10, 2 -> 20, 3 -> 30))(0 -> 0){
  case ((xs, ys), (x,y)) => (xs+x, ys+y)
}
HKFoldLeft(Some(1.1))(0.0)(_+_)
HKFoldLeft(None)(0.0)(_+_)
