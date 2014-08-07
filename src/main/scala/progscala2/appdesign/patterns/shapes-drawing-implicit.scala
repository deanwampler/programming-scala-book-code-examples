// src/main/scala/progscala2/appdesign/patterns/shapes-drawing-implicit.scala

import scala.language.reflectiveCalls
import scala.language.implicitConversions

package progscala2.appdesign.patterns.shapes {  

  class ShapeDrawer(val shape: Shape) {
    def draw() = shape match {
      case c: Circle    => println("Circle.draw: " + c)
      case r: Rectangle => println("Rectangle.draw: " + r)
      case t: Triangle  => println("Triangle.draw: " + t)
    }
  }

  object ShapeDrawer {
    implicit def shape2ShapeDrawer(shape: Shape) = new ShapeDrawer(shape)
  }
}
