// src/main/scala/progscala3/traits/ui2/VetoableClicks.scala
package progscala3.traits.ui2

trait VetoableClicks(val maxAllowed: Int = 1) extends Clickable:     // <1>
  private var count = 0

  abstract override def click(): String =
    if count < maxAllowed then                                       // <2>
      count += 1
      super.click()
    else
      s"Max allowed clicks exceeded: $maxAllowed"
