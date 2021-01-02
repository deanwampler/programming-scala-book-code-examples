// src/script/scala/progscala3/typelessdomore/RepeatedParameters.scala

object Mean1 {
  def calc1(ds: Double*): Double = calc2(ds)
  def calc2(ds: Seq[Double]): Double = ds.sum/ds.size
}

object Mean2 {
  def apply(ds: Double*): Double = apply(ds)
  def apply(ds: Seq[Double]): Double = ds.sum/ds.size
}

object Mean {
  def apply(d: Double, ds: Double*): Double = apply(d +: ds)
  def apply(ds: Seq[Double]): Double = ds.sum/ds.size
}
