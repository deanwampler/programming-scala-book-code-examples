// src/main/scala/ObjectSystem/ui/observable-clicks.scala

package oop.ui

import traits.ui2.Clickable
import traits.observer.Subject

trait ObservableClicks extends Clickable with Subject[Clickable] {
    abstract override def click(): Unit = { 
        super.click()
        notifyObservers(this)
    }
}
