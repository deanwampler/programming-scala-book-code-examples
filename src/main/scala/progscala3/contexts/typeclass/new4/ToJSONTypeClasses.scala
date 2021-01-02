// src/main/scala/progscala3/contexts/typeclass/new4/ToJSONTypeClasses.scala
package progscala3.contexts.typeclass.new4

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}
import progscala3.contexts.json.ToJSON

// This example doesn't appear in the book. It shows yet another refactoring
// to move most of the code into a helper object.

// Protected implementation type, so it's hidden outside this package.
protected object ShapesToJSON:

  // Now the helper state and method from ToJSON is not accessible, so we redefine
  // them here. It would be a good idea to remove them from ToJSON.
  protected val indent = "  "
  protected def indentation(level: Int): (String,String) =
    (indent * level, indent * (level+1))
  protected def handleName(name: String): String =
    if name.length > 0 then s""""$name": """ else ""

  // Point doesn't need to be here, but for consistency, it's here.
  // The compiler complains if I use default values for name and level, with
  // overloaded methods, so I omit them now.
  def apply(point: Point, name: String, level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
      |${indent}"x": "${point.x}",
      |${indent}"y": "${point.y}"
      |$outdent}""".stripMargin

  def apply(circle: Circle, name: String, level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
      |${indent}${circle.center.toJSON("center", level + 1)},
      |${indent}"radius": ${circle.radius}
      |$outdent}""".stripMargin

  def apply(rect: Rectangle, name: String, level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
      |${indent}${rect.lowerLeft.toJSON("lowerLeft", level + 1)},
      |${indent}"height":    ${rect.height}
      |${indent}"width":     ${rect.width}
      |$outdent}""".stripMargin

  def apply(tri: Triangle, name: String, level: Int): String =
    val (outdent, indent) = indentation(level)
    s"""${handleName(name)}{
      |${indent}${tri.point1.toJSON("point1", level + 1)},
      |${indent}${tri.point2.toJSON("point2", level + 1)},
      |${indent}${tri.point3.toJSON("point3", level + 1)},
      |$outdent}""".stripMargin
end ShapesToJSON

given pointToJSON: ToJSON[Point] with
  extension (point: Point)
    def toJSON(name: String = "", level: Int = 0): String =
      ShapesToJSON(point, name, level)

given circleToJSON: ToJSON[Circle] with
  extension (circle: Circle)
    def toJSON(name: String = "", level: Int = 0): String =
      ShapesToJSON(circle, name, level)

given rectangleToJSON: ToJSON[Rectangle] with
  extension (rect: Rectangle)
    def toJSON(name: String = "", level: Int = 0): String =
      ShapesToJSON(rect, name, level)

given triangleToJSON: ToJSON[Triangle] with
  extension (tri: Triangle)
    def toJSON(name: String = "", level: Int = 0): String =
      ShapesToJSON(tri, name, level)

given shapeToJSON: ToJSON[Shape] with
  extension (shape: Shape)
    def toJSON(name: String = "", level: Int = 0): String = shape match
      case c: Circle    => ShapesToJSON(c, name, level)
      case r: Rectangle => ShapesToJSON(r, name, level)
      case t: Triangle  => ShapesToJSON(t, name, level)

@main def TryJSONTypeClasses() =
  val c = Circle(Point(1.0,2.0), 1.0)
  val r = Rectangle(Point(2.0,3.0), 2, 5)
  val t = Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))
  println("==== Use shape.toJSON:")
  Seq(c, r, t).foreach(s => println(s.toJSON("shape", 0)))
  println("==== call toJSON on each shape explicitly:")
  println(c.toJSON("circle", 0))
  println(r.toJSON("rectangle", 0))
  println(t.toJSON("triangle", 0))
