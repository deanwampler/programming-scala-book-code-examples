// src/main/scala/Traits/ui2/observable-clicks.scala

package traits.ui2
import traits.observer._

trait ObservableClicks extends Clickable with Subject[Clickable] {
  abstract override def click(): Unit = {        // <1>
    super.click()
    notifyObservers(this)
  }
}
