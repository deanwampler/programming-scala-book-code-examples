// src/main/scala/Traits/ui2/vetoable-clicks.scala

package traits.ui2
import traits.observer._

trait VetoableClicks extends Clickable {
  val maxAllowed = 1  // default
  private var count = 0

  abstract override def click() = {
    if (count < maxAllowed) {
      count += 1
      super.click()
    }
  }
}
