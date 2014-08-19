// src/main/scala/progscala2/traits/ui2/VetoableClicks.scala
package progscala2.traits.ui2
import progscala2.traits.observer._

trait VetoableClicks extends Clickable {                             // <1>
  // Default number of allowed clicks.
  val maxAllowed = 1                                                 // <2>
  private var count = 0

  abstract override def click() = {
    if (count < maxAllowed) {                                        // <3>
      count += 1
      super.click()
    }
  }
}
