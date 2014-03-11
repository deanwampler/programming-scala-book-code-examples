// src/main/scala/AdvOOP/objects/button.scala

package advoop.objects

import traits.ui2.Clickable

class Button2(val label: String) extends Widget with Clickable {
  
  def updateUI() = { /* Logic to update appearance of the button. */ }
  
  def draw() = {
    // Logic to draw the button on the display, web page, etc.
  }
  
  override def toString() = "(button: label="+label+", "+super.toString()+")"
}

object Button2 {
  def unapply(button: Button2) = Some(button.label)
}
