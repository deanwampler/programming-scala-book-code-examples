// src/main/scala/progscala2/typelessdomore/shapes2/shapes2.scala
package progscala2.typelessdomore.drawing {
  abstract class Drawer {
    import progscala2.typelessdomore.shapes2.Point

    def apply(representation: String, offset: Point = Point(0.0, 0.0)): Unit =
      println(s"draw(offset = $offset), ${this.toString}")
  }
}

package progscala2.typelessdomore.shapes2 {
  case class Point(x: Double = 0.0, y: Double = 0.0) {

    def shift(deltax: Double = 0.0, deltay: Double = 0.0) =
      copy (x + deltax, y + deltay)
  }

  abstract class Shape() {
    /**
     * Draw takes two arguments, one list with an offset for drawing,
     * and the other list that is an implicit parameter for a drawing
     * object, whereas previously we explicitly passed a function argument.
     */
    import progscala2.typelessdomore.drawing.Drawer
    def draw(offset: Point = Point(0.0, 0.0))(implicit drawer: Drawer): Unit =
      drawer(s"draw(offset = $offset), ${this.toString}")

    /**
     * Return a new shape scaled in both x and y dimensions by factor.
     */
    def scale(factor: Double): Shape = {
      assert (factor > 0.0)
      doscale(factor)
    }

    protected def doscale(factor: Double): Shape
  }

  case class Circle(center: Point, radius: Double) extends Shape {

    /** Only the radius changes. */
    def doscale(factor: Double) = copy (radius = radius * factor)
  }

  case class Rectangle(lowerLeft: Point, height: Double, width: Double)
        extends Shape {

    /** Keep the lowerleft point fixed. */
    def doscale(factor: Double) =
      copy (height = height * factor, width = width * factor)
  }
}
