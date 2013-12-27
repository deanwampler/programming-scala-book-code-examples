// src/main/scala/AppDesign/patterns/shapes-drawing-factory.sc

import appdesign.shapes._

val p00 = Point(0.0, 0.0)
val p10 = Point(1.0, 0.0)
val p01 = Point(0.0, 1.0)

val list = List(
    ShapeFactory.makeShape("circle", p00, 5.0),
    ShapeFactory.makeShape("rectangle", p00, 2.0, 3.0),
    ShapeFactory.makeShape("triangle", p00, p10, p01))
    
list foreach { _.draw }
