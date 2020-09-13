// src/main/scala/progscala3/implicits/typeclass/neu/ToJSONTypeClasses.scala
package progscala3.implicits.typeclass.neu    // Can't use keyword "new"!

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}
import progscala3.implicits.json.ToJSON

given ToJSON[Point]:                                            // <1>
  extension (point: Point) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"x": "${point.x}",
      |${indent}"y": "${point.y}"
      |$outdent}""".stripMargin

given ToJSON[Circle]:                                           // <2>
  extension (circle: Circle) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""circle": {
      |${indent}"center": ${circle.center.toJSON(level + 1)},
      |${indent}"radius": ${circle.radius}
      |$outdent}""".stripMargin

given ToJSON[Rectangle]:
  extension (rect: Rectangle) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""rectangle": {
      |${indent}"lowerLeft": ${rect.lowerLeft.toJSON(level + 1)},
      |${indent}"height":    ${rect.height}
      |${indent}"width":     ${rect.width}
      |$outdent}""".stripMargin

given ToJSON[Triangle]:
  extension (tri: Triangle) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""triangle": {
      |${indent}"point1": ${tri.point1.toJSON(level + 1)},
      |${indent}"point2": ${tri.point2.toJSON(level + 1)},
      |${indent}"point3": ${tri.point3.toJSON(level + 1)},
      |$outdent}""".stripMargin

@main def TryJSONNewTypeClasses() =
  println(Circle(Point(1.0,2.0), 1.0).toJSON(0))
  println(Rectangle(Point(2.0,3.0), 2, 5).toJSON(0))
  println(Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0)).toJSON(0))
