// src/main/scala/progscala3/traits/ui2/Button.scala
package progscala3.traits.ui2
import progscala3.traits.ui.Widget

class Button(val label: String) extends Widget with Clickable {

  protected def updateUI(): Unit = { /* logic to change GUI appearance */ }
}
