// tag::point[]
// src/main/scala/progscala3/typelessdomore/shapes/Shapes.scala
package progscala3.typelessdomore.shapes

case class Point(x: Double = 0.0, y: Double = 0.0):             // <1>
  def shift(deltax: Double = 0.0, deltay: Double = 0.0) =       // <2>
    copy(x + deltax, y + deltay)
// end::point[]

// tag::shape[]
abstract class Shape():
  def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit =
    f(s"draw: offset = $offset, shape = ${this}")
// end::shape[]

case class Circle(center: Point, radius: Double) extends Shape

case class Rectangle(lowerLeft: Point, height: Double, width: Double)
  extends Shape

case class Triangle(point1: Point, point2: Point, point3: Point)
      extends Shape
