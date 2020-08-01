// src/main/scala/progscala3/objectsystem/ui/TextField.scala
package progscala3.objectsystem.ui
import progscala3.traits.ui2.Clickable

class TextField(var text: String) extends Widget with Clickable:

    def updateUI(): String = s"""TextField UI with text "$text" updated."""

    def draw(): String = s"""Drawing text "$text""""

    override def toString(): String =
      "(textfield: text="+text+", "+super.toString()+")"

object TextField:
    def unapply(textField: TextField) = Some(textField.text)
