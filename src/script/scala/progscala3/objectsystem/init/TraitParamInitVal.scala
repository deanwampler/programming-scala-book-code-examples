// src/script/scala/progscala3/objectsystem/overrides/TraitParamInitVal.scala

trait AbstractT4(val value: Int):         // <1>
  println("In AbstractT4:")
  def inverse = 1.0/value

val obj4 = new AbstractT4(value = 10):    // <2>
  println("In obj4:")

println("obj4.value = "+obj4.value+", inverse = "+obj4.inverse)
assert(obj4.value   == 10)
assert(obj4.inverse == 0.1)
