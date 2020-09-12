// src/main/scala/progscala3/introscala/shapesactor/Shapes.scala
package progscala3.introscala.shapesactor

case class Point(x: Double = 0.0, y: Double = 0.0)                    // <1>

abstract class Shape():                                               // <2>
  /**
   * Draw the shape.
   * @param f is a function to which the shape will pass a
   * string version of itself to be rendered.
   */
  def draw(f: String => Unit): Unit = f(s"draw: $this")               // <3>

case class Circle(center: Point, radius: Double) extends Shape        // <4>

case class Rectangle(lowerLeft: Point, height: Double, width: Double) // <5>
      extends Shape

case class Triangle(point1: Point, point2: Point, point3: Point)      // <6>
      extends Shape
