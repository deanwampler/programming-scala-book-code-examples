// src/main/scala/progscala3/rounding/LazyInitVal.sc

object ExpensiveResource {
  lazy val resource: Int = init()  
  def init(): Int = { 
    // do something expensive
    0
  }
}
