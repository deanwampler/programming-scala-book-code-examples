// src/main/scala/Traits/ui2/button.scala

package traits.ui2
import traits.ui.Widget

class Button(val label: String) extends Widget with Clickable {
  def click() = {
    // Logic to give the appearance of clicking a button...
  }
}
