// src/main/scala/progscala2/rounding/lazy-init-val.sc

object ExpensiveResource {
  lazy val resource: Int = init()  
  def init(): Int = { 
    // do something expensive
    0
  }
}
