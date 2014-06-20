// src/main/scala/AppDesign/patterns/shapes-drawing-pattern.sc

import appdesign.shapes._

val p00 = Point(0.0, 0.0)
val p10 = Point(1.0, 0.0)
val p01 = Point(0.0, 1.0)

val list = List(Circle(p00, 5.0), 
                Rectangle(p00, 2.0, 3.0),
                Triangle(p00, p10, p01))
    
val drawText = (shape:Shape) => shape match {
  case circle: Circle =>  println("Circle.draw: " + circle)
  case rect: Rectangle => println("Rectangle.draw: " + rect)
  case tri: Triangle =>   println("Triangle.draw: " + tri)
}

def pointToXML(point: Point) =
  "<point><x>%.1f</x><y>%.1f</y></point>".format(point.x, point.y)

val drawXML = (shape:Shape) => shape match {
  case circle: Circle =>  {
    println("<circle>")
    println("  <center>" + pointToXML(circle.center) + "</center>")
    println("  <radius>" + circle.radius + "</radius>")
    println("</circle>")
  }
  case rect: Rectangle => {
    println("<rectangle>")
    println("  <lower-left>" + pointToXML(rect.lowerLeft) + "</lower-left>")
    println("  <height>" + rect.height + "</height>")
    println("  <width>" + rect.width + "</width>")
    println("</rectangle>")
  }
  case tri: Triangle => {
    println("<triangle>")
    println("  <point1>" + pointToXML(tri.point1) + "</point1>")
    println("  <point2>" + pointToXML(tri.point2) + "</point2>")
    println("  <point3>" + pointToXML(tri.point3) + "</point3>")
    println("</triangle>")
  }
}

list foreach (drawText)
println("")
list foreach (drawXML)
