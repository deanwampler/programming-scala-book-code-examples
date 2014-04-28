// src/main/scala/ObjectSystem/ui/widget.scala

package oop.ui

abstract class Widget {
  def draw(): Unit
  override def toString() = "(widget)"
}
