// src/main/scala/progscala3/objectsystem/ui/Button.scala
// This example does not appear in the book. It is designed to demonstrate
// implementing abstract methods and concrete definitions.

package progscala3.objectsystem.ui
import progscala3.traits.ui2.Clickable

open class Button(val label: String) extends Widget with Clickable:

  def draw(): String = s"Drawing: $this"

  protected def updateUI(): String = s"$this clicked; updating UI"

  override def toString(): String =
    s"(button: label=$label, ${super.toString()})"

