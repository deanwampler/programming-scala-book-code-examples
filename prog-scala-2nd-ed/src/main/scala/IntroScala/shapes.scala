// code-examples/IntroducingScala/shapes.scala

package intro.shapes {  
  class Point(val x: Double, val y: Double) {
    override def toString() = "Point(" + x + "," + y + ")"
  }

  abstract class Shape() { 
    def draw(): Unit
  }
  
  class Circle(val center: Point, val radius: Double) extends Shape {
    def draw() = println("Circle.draw: " + this)
    override def toString() = "Circle(" + center + "," + radius + ")"
  }
  
  class Rectangle(val lowerLeft: Point, val height: Double, val width: Double)
        extends Shape {
    def draw() = println("Rectangle.draw: " + this)
    override def toString() = 
      "Rectangle(" + lowerLeft + "," + height + "," + width + ")"
  }

  class Triangle(val point1: Point, val point2: Point, val point3: Point) 
        extends Shape {
    def draw() = println("Triangle.draw: " + this)
    override def toString() = 
      "Triangle(" + point1 + "," + point2 + "," + point3 + ")"
  }
}
