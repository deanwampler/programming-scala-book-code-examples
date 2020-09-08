// src/main/scala/progscala3/objectsystem/ui/Widget.scala
package progscala3.objectsystem.ui

abstract class Widget:
  def draw(): String
  override def toString(): String = "(widget)"
