// src/main/scala/IntroScala/shapes.scala

package intro.shapes {  
  case class Point(x: Double = 0.0, y: Double = 0.0)

  abstract class Shape() { 
    /** 
     * Draw takes a function argument. Each shape will pass a stringized
     * version of itself to this function, which does the "drawing".
     */
    def draw(f: String => Unit): Unit = f(s"draw: ${this.toString}")
  }
  
  case class Circle(center: Point, radius: Double) extends Shape
  
  case class Rectangle(lowerLeft: Point, height: Double, width: Double)
        extends Shape

  class Triangle(val point1: Point, val point2: Point, val point3: Point) 
        extends Shape
}
