// tag::trait[]
// src/main/scala/progscala3/contexts/typeclass/old/ToJSONTypeClasses.scala
package progscala3.contexts.typeclass.old

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}

trait ToJSONOld[T]:
  def toJSON(name: String = "", level: Int = 0): String         // <1>

  protected val indent = "  "
  protected def indentation(level: Int): (String,String) =
    (indent * level, indent * (level+1))
  protected def handleName(name: String): String =
    if name.length > 0 then s""""$name": """ else ""
// end::trait[]

// tag::pointcircle[]
implicit final class PointToJSON(
    point: Point) extends ToJSONOld[Point]:
  def toJSON(name: String = "", level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
      |${indent}"x": "${point.x}",
      |${indent}"y": "${point.y}"
      |$outdent}""".stripMargin

implicit final class CircleToJSON(
    circle: Circle) extends ToJSONOld[Circle]:
  def toJSON(name: String = "", level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
      |${indent}${circle.center.toJSON("center", level + 1)},
      |${indent}"radius": ${circle.radius}
      |$outdent}""".stripMargin
// end::pointcircle[]

implicit final class RectangleToJSON(
    rect: Rectangle) extends ToJSONOld[Rectangle]:
  def toJSON(name: String = "", level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
      |${indent}${rect.lowerLeft.toJSON("lowerLeft", level + 1)},
      |${indent}"height":    ${rect.height}
      |${indent}"width":     ${rect.width}
      |$outdent}""".stripMargin

implicit final class TriangleToJSON(
    tri: Triangle) extends ToJSONOld[Triangle]:
  def toJSON(name: String = "", level: Int = 0): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
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
