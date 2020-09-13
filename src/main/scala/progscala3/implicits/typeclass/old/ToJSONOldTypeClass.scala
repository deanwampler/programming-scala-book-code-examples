// src/main/scala/progscala3/implicits/typeclass/old/ToJSONOldTypeClasses.scala
package progscala3.implicits.typeclass.old

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}
import scala.language.implicitConversions                       // <1>

trait ToJSONOld[T]:
  def toJSON(level: Int): String                                // <2>

  protected val INDENTATION = "  "
  protected def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

implicit final class PointToJSON(
    point: Point) extends ToJSONOld[Point]:                     // <1>
  def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"x": "${point.x}",
      |${indent}"y": "${point.y}"
      |$outdent}""".stripMargin

implicit final class CircleToJSON(
    circle: Circle) extends ToJSONOld[Circle]:                  // <2>
  def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""circle": {
      |${indent}"center": ${circle.center.toJSON(level + 1)},
      |${indent}"radius": ${circle.radius}
      |$outdent}""".stripMargin

implicit final class RectangleToJSON(
    rect: Rectangle) extends ToJSONOld[Rectangle]:
  def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""rectangle": {
      |${indent}"lowerLeft": ${rect.lowerLeft.toJSON(level + 1)},
      |${indent}"height":    ${rect.height}
      |${indent}"width":     ${rect.width}
      |$outdent}""".stripMargin

implicit final class TriangleToJSON(
    tri: Triangle) extends ToJSONOld[Triangle]:
  def toJSON(level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""triangle": {
      |${indent}"point1": ${tri.point1.toJSON(level + 1)},
      |${indent}"point2": ${tri.point2.toJSON(level + 1)},
      |${indent}"point3": ${tri.point3.toJSON(level + 1)},
      |$outdent}""".stripMargin

@main def TryJSONOldTypeClasses() =
  val c = Circle(Point(1.0,2.0), 1.0)
  val r = Rectangle(Point(2.0,3.0), 2, 5)
  val t = Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))
  println(c.toJSON(0))
  println(r.toJSON(0))
  println(t.toJSON(0))
