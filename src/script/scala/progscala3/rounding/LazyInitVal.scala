// src/script/scala/progscala3/rounding/LazyInitVal.scala

object ExpensiveResource {
  lazy val resource: Int = {
    // do something expensive
    0
  }
}
