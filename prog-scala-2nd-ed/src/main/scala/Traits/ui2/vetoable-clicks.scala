// src/main/scala/Traits/ui2/vetoable-clicks.scala

package traits.ui2
import traits.observer._

trait VetoableClicks extends Clickable {         // <1>
  val maxAllowed = 1  // default                    <2>
  private var count = 0

  abstract override def click() = {
    if (count < maxAllowed) {                    // <3>
      count += 1
      super.click()
    }
  }
}
