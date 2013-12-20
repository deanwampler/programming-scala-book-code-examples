// code-examples/AppDesign/patterns/shapes-drawing-implicit.scala

package appdesign.patterns.shapes {  
  class ShapeDrawer(val shape: Shape) {
    def draw = shape match {
      case c: Circle    => println("Circle.draw: " + c)
      case r: Rectangle => println("Rectangle.draw: " + r)
      case t: Triangle  => println("Triangle.draw: " + t)
    }
  }

  object ShapeDrawer {
    implicit def shape2ShapeDrawer(shape: Shape) = new ShapeDrawer(shape)
  }
}
