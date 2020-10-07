// src/main/scala/progscala3/objectsystem/ui/Button.scala
package progscala3.objectsystem.ui
import progscala3.traits.ui2.Clickable

open class Button(val label: String) extends Widget with Clickable:

  def draw(): String = s"Drawing: $this"                             // <1>

  protected def updateUI(): String = s"$this clicked; updating UI"   // <2>

  override def toString(): String =                                  // <3>
    s"(button: label=$label, ${super.toString()})"

