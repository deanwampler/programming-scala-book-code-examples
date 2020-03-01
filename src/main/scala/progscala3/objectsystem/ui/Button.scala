// src/main/scala/progscala3/objectsystem/ui/Button.scala
package progscala3.objectsystem.ui
import progscala3.traits.ui2.Clickable

open class Button(val label: String) extends Widget with Clickable {

  // Simple hack for demonstration purposes:
  def draw(): String = s"Drawing: $this"

  // From Clickable:
  protected def updateUI(): String = s"$this clicked; updating UI"

  override def toString(): String =
    s"(button: label=$label, ${super.toString()})"
}

