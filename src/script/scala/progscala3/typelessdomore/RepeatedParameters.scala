// tag::mean1[]
// src/script/scala/progscala3/typelessdomore/RepeatedParameters.scala

object Mean1:
  def calc1a(ds: Double*): Double = calc1b(ds)
  def calc1b(ds: Seq[Double]): Double = ds.sum/ds.size

  def calc2a(ds: Double*): Double = ds.sum/ds.size
  def calc2b(ds: Seq[Double]): Double = calc2a(ds*)
// end::mean1[]

// tag::mean2[]
object Mean2:
  def apply(ds: Double*): Double = apply(ds)
  def apply(ds: Seq[Double]): Double = ds.sum/ds.size
// end::mean2[]

// tag::mean[]
object Mean:
  def apply(d: Double, ds: Double*): Double = apply(d +: ds)
  def apply(ds: Seq[Double]): Double = ds.sum/ds.size
// end::mean[]
