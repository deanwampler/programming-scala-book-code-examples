// src/main/scala/AppDesign/patterns/shapes-drawing-visitor.scala

package appdesign.patterns.shapes.visitor {
	
	class ShapeDrawingVisitor extends ShapeVisitor {
    def visit(circle: Circle): Unit = 
      println("Circle.draw: " + circle)
    
    def visit(rect: Rectangle): Unit =
      println("Rectangle.draw: " + rect)

    def visit(tri: Triangle): Unit =
      println("Triangle.draw: " + tri)
  }
}
