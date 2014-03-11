// src/main/scala/Traits/ui2/button.scala

package traits.ui2
import traits.ui.Widget

class Button(val label: String) extends Widget with Clickable {

  protected def updateUI(): Unit = { /* logic to change GUI appearance */ }
}
