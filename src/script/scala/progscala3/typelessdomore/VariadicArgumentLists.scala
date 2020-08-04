// src/script/scala/progscala3/typelessdomore/VariadicArgumentLists.scala

object Mean1 {
  def calc1(ds: Double*): Double = calc2(ds :_*)
  def calc2(ds: Seq[Double]): Double = ds.sum/ds.size
}

object Mean2 {
  def apply(ds: Double*): Double = apply(ds :_*)
  def apply(ds: Seq[Double]): Double = ds.sum/ds.size
}

object Mean {
  def apply(d: Double, ds: Double*): Double = apply(d +: ds)
  def apply(ds: Seq[Double]): Double = ds.sum/ds.size
}
