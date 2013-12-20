// code-examples/AppDesign/patterns/shapes-drawing-factory.scala

package appdesign.patterns.shapes {
  trait Drawing {
    def draw: Unit
  }

  trait CircleDrawing extends Drawing {
    def draw = println("Circle.draw " + this)
  }
  trait RectangleDrawing extends Drawing {
    def draw = println("Rectangle.draw: " + this)
  }
  trait TriangleDrawing extends Drawing {
    def draw = println("Triangle.draw: " + this)
  }
  
  object ShapeFactory {
    def makeShape(args: Any*) = args(0) match {
      case "circle" => {
        val center = args(1).asInstanceOf[Point]
        val radius = args(2).asInstanceOf[Double]
        new Circle(center, radius) with CircleDrawing
      }
      case "rectangle" => {
        val lowerLeft = args(1).asInstanceOf[Point]
        val height    = args(2).asInstanceOf[Double]
        val width     = args(3).asInstanceOf[Double]
        new Rectangle(lowerLeft, height, width) with RectangleDrawing
      }
      case "triangle" => {
        val p1 = args(1).asInstanceOf[Point]
        val p2 = args(2).asInstanceOf[Point]
        val p3 = args(3).asInstanceOf[Point]
        new Triangle(p1, p2, p3) with TriangleDrawing
      }
      case x => throw new IllegalArgumentException("unknown: " + x)
    }
  }
}
