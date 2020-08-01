// src/main/scala/progscala3/objectsystem/ui/ObservableClicks.scala
package progscala3.objectsystem.ui
import progscala3.traits.ui2.Clickable
import progscala3.traits.observer.Subject

trait ObservableClicks extends Clickable with Subject[Clickable]:
  abstract override def click(): String =
    val result = super.click()
    notifyObservers(this)
    result
