// code-examples/AppDesign/patterns/shapes.scala

package appdesign.patterns.shapes {  
  case class Point(x: Double, y: Double)

  sealed abstract class Shape()
  
  case class Circle(center: Point, radius: Double) extends Shape()
  
  case class Rectangle(lowerLeft: Point, height: Double, width: Double)
      extends Shape() 
  
  case class Triangle(point1: Point, point2: Point, point3: Point) 
      extends Shape() 
}
