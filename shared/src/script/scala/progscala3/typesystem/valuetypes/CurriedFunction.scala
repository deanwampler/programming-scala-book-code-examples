// src/script/scala/progscala3/typesystem/valuetypes/CurriedFunction.scala

val f  = (x: Double, y: Double, z: Double) => x * y / z
val fc = f.curried

assert(f(2.0, 5.0, 4.0) == fc(2.0)(5.0)(4.0))

val fc1 = fc(2.0)                                          // <1>
val fc2 = fc1(5.0)                                         // <2>
val answer = fc2(4.0)                                      // <3>
assert(answer == f(2.0, 5.0, 4.0))
assert(answer == fc(2.0)(5.0)(4.0))
