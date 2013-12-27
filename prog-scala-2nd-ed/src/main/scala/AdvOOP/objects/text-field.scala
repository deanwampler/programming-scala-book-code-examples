// src/main/scala/AdvOOP/objects/text-field.scala

package advoop.objects
import advoop.ui.Clickable

class TextField(var text: String) extends Widget with Clickable {
    
    def click() = {
        // ... logic to select the appropriate point in the text.
    }
    
    def draw() = {
        // ... logic to draw the text field on the display, web page, etc.
    }
    
    override def toString() = "(textfield: text="+text+", "+super.toString()+")"
}

object TextField {
    def unapply(textField: TextField) = Some(textField.text)
}
