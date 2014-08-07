// src/main/scala/progscala2/appdesign/patterns/shapes-drawing-implicit.sc

import progscala2.appdesign.shapes._
import scala.language.reflectiveCalls
import scala.language.implicitConversions

val p00 = Point(0.0, 0.0)
val p10 = Point(1.0, 0.0)
val p01 = Point(0.0, 1.0)

val list = List(Circle(p00, 5.0), 
                Rectangle(p00, 2.0, 3.0),
                Triangle(p00, p10, p01))
    
import shapes2.ShapeDrawer._

list foreach { _.draw }
