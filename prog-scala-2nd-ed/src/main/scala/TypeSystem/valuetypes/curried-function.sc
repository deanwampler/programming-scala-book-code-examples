// code-examples/TypeSystem/valuetypes/curried-function-script.scala

val f  = (x: Double, y: Double, z: Double) => x * y / z
val fc = f.curry

val answer1 = f(2., 5., 4.)
val answer2 = fc(2.)(5.)(4.)
println( answer1 + " == " + answer2 + "? " + (answer1 == answer2))

val fc1 = fc(2.)
val fc2 = fc1(5.)
val answer3 = fc2(4.)
println( answer3 + " == " + answer2 + "? " + (answer3 == answer2))
