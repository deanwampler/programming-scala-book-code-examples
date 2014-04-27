// src/main/scala/AdvOOP/ui/observable-clicks.scala

package advoop.ui

import traits.ui2.Clickable
import traits.observer.Subject

trait ObservableClicks extends Clickable with Subject[Clickable] {
    abstract override def click(): Unit = { 
        super.click()
        notifyObservers(this)
    }
}
