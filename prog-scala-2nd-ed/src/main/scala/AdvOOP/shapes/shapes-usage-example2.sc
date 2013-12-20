// code-examples/AdvOOP/shapes/shapes-usage-example2-script.scala

import advoop.shapes._

val shapesList = List(
  Circle(Point(0.0, 0.0), 1.0),
  Circle(Point(5.0, 2.0), 3.0),
  Rectangle(Point(0.0, 0.0), 2, 5),
  Rectangle(Point(-2.0, -1.0), 4, 3),
  Triangle(Point(0.0, 0.0), Point(1.0, 0.0), Point(0.0, 1.0)))

def matchOn(shape: Shape) = shape match {
  case Circle(center, radius) => 
    println("Circle: center = "+center+", radius = "+radius)
  case Rectangle(ll, h, w) => 
    println("Rectangle: lower-left = "+ll+", height = "+h+", width = "+w)
  case Triangle(p1, p2, p3) => 
    println("Triangle: point1 = "+p1+", point2 = "+p2+", point3 = "+p3)
  case _ =>
    println("Unknown shape!"+shape)
}

shapesList.foreach { shape => matchOn(shape) }
