// src/main/scala/progscala3/traits/ui2/VetoableClicks.scala
package progscala3.traits.ui2

trait VetoableClicks(val maxAllowed: Int = 1) extends Clickable:     // <1>
  private var count = 0                                              // <2>

  abstract override def click(): String =
    count.synchronized { count += 1 }
    if count <= maxAllowed then                                      // <3>
      super.click()
    else
      s"Max allowed clicks $maxAllowed exceeded. Received $count clicks!"

  def resetCount(): Unit = count.synchronized { count = 0 }          // <4>

