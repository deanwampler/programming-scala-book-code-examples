// src/main/scala/progscala2/traits/ui2/Button.scala
package progscala2.traits.ui2
import progscala2.traits.ui.Widget

class Button(val label: String) extends Widget with Clickable {

  protected def updateUI(): Unit = { /* logic to change GUI appearance */ }
}
