// src/main/scala/progscala3/traits/ui/Button.scala
package progscala3.traits.ui

abstract class Button(val label: String) extends Widget:
  def click(): Unit = updateUI()
  protected def updateUI(): Unit
