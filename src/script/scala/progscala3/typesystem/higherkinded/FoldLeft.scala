// tag::definitions[]
// src/script/scala/progscala3/typesystem/higherkinded/FoldLeft.scala

object FoldLeft:
  def apply[IN, OUT](seq: Seq[IN])(seed: OUT)(f: (OUT, IN) => OUT): OUT =
    var accumulator = seed
    seq.foreach(t => accumulator = f(accumulator, t))
    accumulator

  def apply[IN, OUT](opt: Option[IN])(seed: OUT)(f: (OUT, IN) => OUT): OUT =
    opt match
      case Some(t) => f(seed, t)
      case None => seed
// end::definitions[]

FoldLeft(List(1, 2, 3))(0)(_+_)
FoldLeft(List(1, 2, 3))("(0)")((s, i) => s"($s $i)")
FoldLeft(Array(1, 2, 3).toSeq)(0)(_+_)                     // <1>
FoldLeft(Vector(1 -> "one", 2 -> "two", 3 -> "three"))(0 -> "(0)"){
  case ((xs, ys), (x,y)) => (xs+x, s"($ys $y)")
}
FoldLeft(Some(1.1))(0.0)(_+_)
FoldLeft(Option.empty[Int])(0.0)(_+_)                      // <2>
