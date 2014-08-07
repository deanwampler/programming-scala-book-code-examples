// src/main/scala/progscala2/appdesign/patterns/shapes-drawing-visitor.sc

import progscala2.appdesign.shapes._

val p00 = Point(0.0, 0.0)
val p10 = Point(1.0, 0.0)
val p01 = Point(0.0, 1.0)

val list = List(Circle(p00, 5.0), 
                Rectangle(p00, 2.0, 3.0),
                Triangle(p00, p10, p01))
    
val shapesDrawer = new ShapeDrawingVisitor
list foreach { _.accept(shapesDrawer) }
