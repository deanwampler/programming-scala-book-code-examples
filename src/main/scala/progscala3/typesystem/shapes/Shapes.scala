// src/main/scala/progscala3/typesystem/shapes/Shapes.scala
package progscala3.typesystem.shapes

case class Point(x: Double, y: Double):
  override def toString(): String = "Point(" + x + "," + y + ")"

abstract class Shape():
  def draw(): String

case class Circle(center: Point, radius: Double) extends Shape:
  def draw(): String = "Circle.draw: " + this

case class Rectangle(lowerLeft: Point, height: Double, width: Double)
      extends Shape:
  def draw(): String = "Rectangle.draw: " + this

case class Triangle(point1: Point, point2: Point, point3: Point)
      extends Shape():
  def draw(): String = "Triangle.draw: " + this
