// src/main/scala/progscala3/implicits/typeclass/neu3/ToJSONTypeClasses.scala
package progscala3.implicits.typeclass.neu3

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}
import progscala3.implicits.json.ToJSON

given ToJSON[Point]:
  extension (point: Point) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"x": "${point.x}",
      |${indent}"y": "${point.y}"
      |$outdent}""".stripMargin

given ToJSON[Shape]:
  extension (shape: Shape) def toJSON(level: Int): String = shape match
    case c: Circle    => circleToJSON.extension_toJSON(c)(level)
    case r: Rectangle => rectangleToJSON.extension_toJSON(r)(level)
    case t: Triangle  => triangleToJSON.extension_toJSON(t)(level)

given circleToJSON as ToJSON[Circle]:
  extension (circle: Circle) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""circle": {
      |${indent}"center": ${circle.center.toJSON(level + 1)},
      |${indent}"radius": ${circle.radius}
      |$outdent}""".stripMargin

given rectangleToJSON as ToJSON[Rectangle]:
  extension (rect: Rectangle) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""rectangle": {
      |${indent}"lowerLeft": ${rect.lowerLeft.toJSON(level + 1)},
      |${indent}"height":    ${rect.height}
      |${indent}"width":     ${rect.width}
      |$outdent}""".stripMargin

given triangleToJSON as ToJSON[Triangle]:
  extension (tri: Triangle) def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""triangle": {
      |${indent}"point1": ${tri.point1.toJSON(level + 1)},
      |${indent}"point2": ${tri.point2.toJSON(level + 1)},
      |${indent}"point3": ${tri.point3.toJSON(level + 1)},
      |$outdent}""".stripMargin

@main def TryJSONNewTypeClasses() =
  val c = Circle(Point(1.0,2.0), 1.0)
  val r = Rectangle(Point(2.0,3.0), 2, 5)
  val t = Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))
  println("==== Use shape.toJSON:")
  Seq(c, r, t).foreach(s => println(s.toJSON(0)))
  println("==== call toJSON on each shape explicitly:")
  println(c.toJSON(0))
  println(r.toJSON(0))
  println(t.toJSON(0))
