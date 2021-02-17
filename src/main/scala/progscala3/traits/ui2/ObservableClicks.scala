// src/main/scala/progscala3/traits/ui2/ObservableClicks.scala
package progscala3.traits.ui2
import progscala3.traits.observer.*

trait ObservableClicks extends Clickable with Subject[Clickable]:
  abstract override def click(): String =                            // <1>
    val result = super.click()
    notifyObservers(this)
    result
