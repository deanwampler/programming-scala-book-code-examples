// src/main/scala/TypeSystem/abstracttypes/shapes-events.sc

import typesystem.shapes._

object EventKind extends Enumeration {
  type EventKind = Value  // Convenient type alias

  val Draw = Value("Draw")
  val Remove = Value("Remove")
  val Resize = Value("Resize")
  val Rotate = Value("Rotate")
}

import EventKind._  // Expose members of enum. in scope 

case class Event[T](val kind: EventKind, val payload: T)

trait ShapeEvent {
  type s <: Shape
  val shape: s
  val kind: EventKind
  val args: List[Any] = Nil  // resize percentage, angle of rotation, etc.
}

abstract class CircleEvent extends ShapeEvent {
  type s = Circle
}
case class DrawCircleEvent(val shape: Circle) extends CircleEvent {
  val kind = Draw
}
case class RemoveCircleEvent(val shape: Circle) extends CircleEvent {
  val kind = Remove
}
case class ResizeCircleEvent(val shape: Circle, percentage: Double) 
    extends CircleEvent {
  val kind = Resize
  override val args = List(percentage)
}
case class RotateCircleEvent(val shape: Circle, angle: Double) 
    extends CircleEvent {
  val kind = Rotate
  override val args = List(angle)
}
// similarly for other shapes

val circle1 = new Circle(Point(0.0, 0.0), 5.0)
val circle2 = new Circle(Point(2.0, 2.0), 3.0)

List(
  new DrawCircleEvent(circle1),
  new DrawCircleEvent(circle2),
  new RotateCircleEvent(circle1, 30.0),
  new ResizeCircleEvent(circle2, 200.0),
  new RemoveCircleEvent(circle2),
  new RemoveCircleEvent(circle1)).foreach { event => println(event) }
  
