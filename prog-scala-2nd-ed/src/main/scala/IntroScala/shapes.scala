// code-examples/IntroducingScala/shapes.scala

package intro.shapes {  
  case class Point(x: Double, y: Double)

  abstract class Shape() { 
    /** 
     * Draw takes a function argument. Each shape will pass a stringized
     * version of itself to this function, which does the "drawing".
     */
    def draw(f: String => Unit): Unit
  }
  
  case class Circle(center: Point, radius: Double) extends Shape {
    def draw(f: String => Unit) = f(s"Circle.draw: $this")
  }
  
  case class Rectangle(lowerLeft: Point, height: Double, width: Double)
        extends Shape {
    def draw(f: String => Unit) = f(s"Rectangle.draw: $this")
  }

  class Triangle(val point1: Point, val point2: Point, val point3: Point) 
        extends Shape {
    def draw(f: String => Unit) = f(s"Triangle.draw: $this")
  }
}
