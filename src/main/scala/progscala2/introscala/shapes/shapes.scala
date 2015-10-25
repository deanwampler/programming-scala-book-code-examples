// src/main/scala/progscala2/introscala/shapes/shapes.scala
package progscala2.introscala.shapes

case class Point(x: Double = 0.0, y: Double = 0.0)                   // <1>

abstract class Shape() {                                             // <2>
  /**
   * Draw takes a function argument. Each shape will pass a stringized
   * version of itself to this function, which does the "drawing".
   */
  def draw(f: String => Unit): Unit = f(s"draw: ${this.toString}")   // <3>
}

case class Circle(center: Point, radius: Double) extends Shape       // <4>

case class Rectangle(lowerLeft: Point, height: Double, width: Double) // <5>
      extends Shape

case class Triangle(point1: Point, point2: Point, point3: Point)     // <6>
      extends Shape
