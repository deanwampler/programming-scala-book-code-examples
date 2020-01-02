// src/main/scala/progscala3/traits/ui/Button.scala
package progscala3.traits.ui

class Button(val label: String) extends Widget {

  def click(): Unit = updateUI()

  def updateUI(): Unit = { /* logic to change GUI appearance */ }
}
