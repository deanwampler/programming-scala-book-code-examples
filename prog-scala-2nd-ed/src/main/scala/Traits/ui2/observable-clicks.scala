// src/main/scala/Traits/ui2/observable-clicks.scala

package traits.ui2
import traits.observer._

trait ObservableClicks extends Clickable with Subject {
  abstract override def click() = {
    super.click()
    notifyObservers
  }
}
