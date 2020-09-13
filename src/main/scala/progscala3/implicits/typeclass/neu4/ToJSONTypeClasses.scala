// src/main/scala/progscala3/implicits/typeclass/neu4/ToJSONTypeClasses.scala
package progscala3.implicits.typeclass.neu4

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}
import progscala3.implicits.json.ToJSON

// Refactoring to move most of the code into a helper object.

// Protected implementation type, so it's hidden outside this package.
protected object ShapesToJSON:

  // Now the helper state and method from ToJSON is not accessible, so we redefine
  // them here. It would be a good idea to remove them from ToJSON.
  protected val INDENTATION = "  "
  protected def indentation(level: Int): (String,String) =
    (INDENTATION * level, INDENTATION * (level+1))

  // Point doesn't need to be here, but for consistency...
  def apply(point: Point, level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""{
      |${indent}"x": "${point.x}",
      |${indent}"y": "${point.y}"
      |$outdent}""".stripMargin

  def apply(circle: Circle, level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""circle": {
      |${indent}"center": ${circle.center.toJSON(level + 1)},
      |${indent}"radius": ${circle.radius}
      |$outdent}""".stripMargin

  def apply(rect: Rectangle, level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""rectangle": {
      |${indent}"lowerLeft": ${rect.lowerLeft.toJSON(level + 1)},
      |${indent}"height":    ${rect.height}
      |${indent}"width":     ${rect.width}
      |$outdent}""".stripMargin

  def apply(tri: Triangle, level: Int): String =
    val (outdent, indent) = indentation(level)
    s""""triangle": {
      |${indent}"point1": ${tri.point1.toJSON(level + 1)},
      |${indent}"point2": ${tri.point2.toJSON(level + 1)},
      |${indent}"point3": ${tri.point3.toJSON(level + 1)},
      |$outdent}""".stripMargin


given ToJSON[Point]:
  extension (point: Point) def toJSON(level: Int): String =
    ShapesToJSON(point, level)

given ToJSON[Shape]:
  extension (shape: Shape) def toJSON(level: Int): String = shape match
    case c: Circle    => ShapesToJSON(c, level)
    case r: Rectangle => ShapesToJSON(r, level)
    case t: Triangle  => ShapesToJSON(t, level)

given ToJSON[Circle]:                                           // <2>
  extension (circle: Circle) def toJSON(level: Int): String =
    ShapesToJSON(circle, level)

given ToJSON[Rectangle]:
  extension (rect: Rectangle) def toJSON(level: Int): String =
    ShapesToJSON(rect, level)

given ToJSON[Triangle]:
  extension (tri: Triangle) def toJSON(level: Int): String =
    ShapesToJSON(tri, level)

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
