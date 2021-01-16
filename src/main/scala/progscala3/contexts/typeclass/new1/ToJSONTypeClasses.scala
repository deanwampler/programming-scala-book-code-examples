// tag::definitions1[]
// src/main/scala/progscala3/contexts/typeclass/new1/ToJSONTypeClasses.scala
package progscala3.contexts.typeclass.new1

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}
import progscala3.contexts.json.ToJSON

given ToJSON[Point] with                                             // <1>
  extension (point: Point)
    def toJSON(name: String = "", level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""${handleName(name)}{
        |${indent}"x": "${point.x}",
        |${indent}"y": "${point.y}"
        |$outdent}""".stripMargin

given ToJSON[Circle] with                                            // <2>
  extension (circle: Circle)
    def toJSON(name: String = "", level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""${handleName(name)}{
        |${indent}${circle.center.toJSON("center", level + 1)},
        |${indent}"radius": ${circle.radius}
        |$outdent}""".stripMargin
// end::definitions1[]

// tag::definitions2[]
given ToJSON[Rectangle] with
  extension (rect: Rectangle)
    def toJSON(name: String = "", level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""${handleName(name)}{
        |${indent}${rect.lowerLeft.toJSON("lowerLeft", level + 1)},
        |${indent}"height":    ${rect.height}
        |${indent}"width":     ${rect.width}
        |$outdent}""".stripMargin

given ToJSON[Triangle] with
  extension (tri: Triangle)
    def toJSON(name: String = "", level: Int = 0): String =
      val (outdent, indent) = indentation(level)
      s"""${handleName(name)}{
        |${indent}${tri.point1.toJSON("point1", level + 1)},
        |${indent}${tri.point2.toJSON("point2", level + 1)},
        |${indent}${tri.point3.toJSON("point3", level + 1)},
        |$outdent}""".stripMargin
// end::definitions2[]

// tag::main[]
@main def TryJSONTypeClasses() =
  println(s"summon[ToJSON[Point]] = ${summon[ToJSON[Point]]}")      // <1>
  println(s"summon[ToJSON[Circle]] = ${summon[ToJSON[Circle]]}")
  println(Circle(Point(1.0,2.0), 1.0).toJSON("circle", 0))
  println(Rectangle(Point(2.0,3.0), 2, 5).toJSON("rectangle", 0))
  println(Triangle(
    Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0)).toJSON("triangle", 0))
// end::main[]
