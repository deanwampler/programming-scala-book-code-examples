// src/main/scala/progscala3/traits/ui2/VetoableClicks.scala
package progscala3.traits.ui2

trait VetoableClicks extends Clickable {                             // <1>
  // Default number of allowed clicks.
  val maxAllowed = 1                                                 // <2>
  private var count = 0

  abstract override def click(): String = {
    if count < maxAllowed then {                                        // <3>
      count += 1
      super.click()
    } else {
      s"Max allowed clicks exceeded: $maxAllowed"
    }
  }
}
