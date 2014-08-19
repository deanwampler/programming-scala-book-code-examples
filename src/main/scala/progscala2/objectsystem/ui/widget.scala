// src/main/scala/progscala2/objectsystem/ui/Widget.scala
package progscala2.objectsystem.ui

abstract class Widget {
  def draw(): Unit
  override def toString() = "(widget)"
}
