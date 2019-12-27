// src/main/scala/progscala3/objectsystem/ui/Widget.scala
package progscala3.objectsystem.ui

abstract class Widget {
  def draw(): Unit
  override def toString() = "(widget)"
}
