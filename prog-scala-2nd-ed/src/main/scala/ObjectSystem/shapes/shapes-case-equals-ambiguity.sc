// src/main/scala/ObjectSystem/shapes/shapes-case-equals-ambiguity.sc

import oop.shapes.id._

case class FancyCircle(name: String, override val id: String, 
    override val center: Point, override val radius: Double)
      extends Circle(id, center, radius) {
  override def draw() = println("FancyCircle.draw: " + this)
}

val fc = FancyCircle("me", "circle", Point(0.0,0.0), 10.0)
val c  = Circle("circle", Point(0.0,0.0), 10.0)
format("FancyCircle == Circle? %b\n", (fc == c))
format("Circle == FancyCircle? %b\n", (c  == fc))
