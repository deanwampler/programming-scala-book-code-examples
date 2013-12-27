// src/main/scala/AdvOOP/ui/observable-clicks.scala

package advoop.ui

import traits.observer._

trait ObservableClicks extends Clickable with Subject {
    abstract override def click() = { 
        super.click()
        notifyObservers
    }
}
