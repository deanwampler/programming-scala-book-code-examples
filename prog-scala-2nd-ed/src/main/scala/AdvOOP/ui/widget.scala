// src/main/scala/AdvOOP/ui/widget.scala

package advoop.ui

abstract class Widget {
  def draw(): Unit
  override def toString() = "(widget)"
}

