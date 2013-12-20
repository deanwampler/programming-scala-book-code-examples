// code-examples/AdvOOP/ui3/button.scala

package advoop.ui

class Button(val label: String) extends Widget with Clickable {
    
  def click() = {
    // Logic to give the appearance of clicking a button...
  }
  
  def draw() = {
    // Logic to draw the button on the display, web page, etc.
  }
  
  override def toString() = 
    "(button: label=" + label + ", " + super.toString() + ")"
}
