// src/test/scala/progscala3/contexts/typeclass/old/ToJSONOldTypeClassesSuite.scala
package progscala3.contexts.typeclass.old

import progscala3.introscala.shapes.{Point, Shape, Circle, Rectangle, Triangle}
import munit.*

class ToJSONOldTypeClassesSuite extends FunSuite:
  val c = Circle(Point(1.0,2.0), 1.0)
  val r = Rectangle(Point(2.0,3.0), 2, 5)
  val t = Triangle(Point(0.0,0.0), Point(2.0,0.0), Point(1.0,2.0))

  def ns(s: String) = s.replaceAll("\\s+", "")  // remove white space

  test("An extension method is a good way to add a custom toJSON method") {
    assert(ns(c.toJSON("circle", 0)) == ns(""""circle": {
      "center": {
        "x": "1.0",
        "y": "2.0"
      },
      "radius": 1.0
    }"""))
    assert(ns(r.toJSON("rectangle", 0)) == ns(""""rectangle": {
      "lowerLeft": {
        "x": "2.0",
        "y": "3.0"
      },
      "height":    2.0
      "width":     5.0
    }"""))
    assert(ns(t.toJSON("triangle", 0)) == ns(""""triangle": {
      "point1": {
        "x": "0.0",
        "y": "0.0"
      },
      "point2": {
        "x": "2.0",
        "y": "0.0"
      },
      "point3": {
        "x": "1.0",
        "y": "2.0"
      },
    }"""))
  }
