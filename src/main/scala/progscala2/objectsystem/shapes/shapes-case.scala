// src/main/scala/progscala2/objectsystem/shapes/shapes-case.scala
package progscala2.objectsystem.shapes {
  case class Point(x: Double, y: Double)

  abstract class Shape() {
    def draw(): Unit
  }

  case class Circle(center: Point, radius: Double) extends Shape() {
    def draw() = println("Circle.draw: " + this)
  }

  case class Rectangle(lowerLeft: Point, height: Double, width: Double)
      extends Shape() {
    def draw() = println("Rectangle.draw: " + this)
  }

  case class Triangle(point1: Point, point2: Point, point3: Point)
      extends Shape() {
    def draw() = println("Triangle.draw: " + this)
  }
}
