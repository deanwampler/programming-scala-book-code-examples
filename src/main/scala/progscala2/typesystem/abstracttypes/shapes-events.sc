// src/main/scala/progscala2/typesystem/abstracttypes/shapes-events.sc
import progscala2.typesystem.shapes._

// Kinds of events
object EventKind extends Enumeration {
  type EventKind = Value  // Convenient type alias

  val Draw   = Value("Draw")
  val Remove = Value("Remove")
  val Resize = Value("Resize")
  val Rotate = Value("Rotate")
}

import EventKind._  // Expose members of enum. in scope

trait Event {
  type s <: Shape                                                    // <1>
  val shape: s                                                       // <2>
  val kind: EventKind                                                // <3>
  val args: List[Any] = Nil                                          // <4>
}

trait CircleEvent extends Event {                                    // <5>
  type s = Circle
}
case class DrawCircle(val shape: Circle) extends CircleEvent {
  val kind = Draw
}
case class RemoveCircle(val shape: Circle) extends CircleEvent {
  val kind = Remove
}
case class ResizeCircle(val shape: Circle, percent: Double) extends CircleEvent{
  val kind = Resize
  override val args = List(percent)
}
case class RotateCircle(val shape: Circle, angle: Double) extends CircleEvent{
  val kind = Rotate
  override val args = List(angle)
}

val circle1 = new Circle(Point(0.0, 0.0), 5.0)
val circle2 = new Circle(Point(2.0, 2.0), 3.0)

List(
  new DrawCircle(circle1),
  new DrawCircle(circle2),
  new RotateCircle(circle1, 30.0),
  new ResizeCircle(circle2, 200.0),
  new RemoveCircle(circle2),
  new RemoveCircle(circle1)).foreach { event => println(event) }

