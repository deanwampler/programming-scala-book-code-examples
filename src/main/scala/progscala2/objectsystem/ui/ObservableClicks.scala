// src/main/scala/progscala2/objectsystem/ui/ObservableClicks.scala
package progscala2.objectsystem.ui
import progscala2.traits.ui2.Clickable
import progscala2.traits.observer.Subject

trait ObservableClicks extends Clickable with Subject[Clickable] {
    abstract override def click(): Unit = {
        super.click()
        notifyObservers(this)
    }
}
