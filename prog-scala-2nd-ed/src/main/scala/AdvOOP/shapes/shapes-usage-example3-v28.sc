// code-examples/AdvOOP/shapes/shapes-usage-example3-v28-script.scala
// Scala version 2.8 only.

import advoop.shapes._

val circle1 = Circle(Point(0.0, 0.0), 2.0)
val circle2 = circle1 copy (radius = 4.0)

println(circle1)
println(circle2)
