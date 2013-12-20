// code-examples/AppDesign/patterns/shapes-visitor.scala

package appdesign.patterns.shapes.visitor {  
  trait ShapeVisitor {
    def visit(circle: Circle): Unit
    def visit(rect: Rectangle): Unit
    def visit(tri: Triangle): Unit
  }

  case class Point(x: Double, y: Double)

  sealed abstract class Shape() { 
    def accept(visitor: ShapeVisitor): Unit
  }
  
  case class Circle(center: Point, radius: Double) extends Shape() {
    def accept(visitor: ShapeVisitor) = visitor.visit(this)
  }
  
  case class Rectangle(lowerLeft: Point, height: Double, width: Double)
        extends Shape() {
    def accept(visitor: ShapeVisitor) = visitor.visit(this)
  }
  
  case class Triangle(point1: Point, point2: Point, point3: Point) 
        extends Shape() {
    def accept(visitor: ShapeVisitor) = visitor.visit(this)
  }
}
