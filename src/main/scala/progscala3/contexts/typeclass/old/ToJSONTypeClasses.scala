// tag::trait[]
// src/main/scala/progscala3/contexts/typeclass/old/ToJSONTypeClasses.scala
package progscala3.contexts.typeclass.old

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}

// DeanW: September 14, 2025. Scala is dropping support for implicit classes, so the 
// `implicit final class PointToJSON`, etc. are now replaced by extension methods
// and the trait ToJSONOld is converted to an object for its methods.

object ToJSONOld:
  val indent = "  "
  def indentation(level: Int): (String,String) =
    (indent * level, indent * (level+1))
  def handleName(name: String): String =
    if name.length > 0 then s""""$name": """ else ""
end ToJSONOld

extension(point: Point)
  def toJSON(name: String, level: Int): String =
    val (outdent, indent) = ToJSONOld.indentation(level)
    s"""${ToJSONOld.handleName(name)}{
      |${indent}"x": "${point.x}",
      |${indent}"y": "${point.y}"
      |$outdent}""".stripMargin

extension(circle: Circle)
  def toJSON(name: String, level: Int): String =
    val (outdent, indent) = ToJSONOld.indentation(level)
    s"""${ToJSONOld.handleName(name)}{
      |${indent}${circle.center.toJSON("center", level + 1)},
      |${indent}"radius": ${circle.radius}
      |$outdent}""".stripMargin

extension(rect: Rectangle)
  def toJSON(name: String, level: Int): String =
    val (outdent, indent) = ToJSONOld.indentation(level)
    s"""${ToJSONOld.handleName(name)}{
      |${indent}${rect.lowerLeft.toJSON("lowerLeft", level + 1)},
      |${indent}"height":    ${rect.height}
      |${indent}"width":     ${rect.width}
      |$outdent}""".stripMargin

extension(tri: Triangle)
  def toJSON(name: String, level: Int): String =
    val (outdent, indent) = ToJSONOld.indentation(level)
    s"""${ToJSONOld.handleName(name)}{
      |${indent}${tri.point1.toJSON("point1", level + 1)},
      |${indent}${tri.point2.toJSON("point2", level + 1)},
      |${indent}${tri.point3.toJSON("point3", level + 1)},
      |$outdent}""".stripMargin

// tag::main[]
@main def TryJSONTypeClasses() =
  val c = Circle(Point(1.0,2.0), 1.0)
  val r = Rectangle(Point(2.0,3.0), 2, 5)
  val t = Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))
  println(c.toJSON("circle", 0))
  println(r.toJSON("rectangle", 0))
  println(t.toJSON("triangle", 0))
// end::main[]
