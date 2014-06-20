// src/main/scala/ObjectSystem/ui/button.scala

package oop.ui

import traits.ui2.Clickable

class Button(val label: String) extends Widget with Clickable {

  // Simple hack for demonstration purposes:
  def draw(): Unit = println(s"Drawing: $this")

  // From Clickable:
  protected def updateUI(): Unit = println(s"$this clicked; updating UI")

  override def toString() = s"(button: label=$label, ${super.toString()})"
}

