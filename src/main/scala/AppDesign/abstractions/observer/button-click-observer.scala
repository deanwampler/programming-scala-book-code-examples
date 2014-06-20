// src/main/scala/AppDesign/abstractions/observer/button-click-observer.scala

package appdesign.abstractions.observer
import ButtonSubjectObserver._
import traits.ui.Button

class ButtonClickObserver extends ButtonObserver {
 val clicks = new scala.collection.mutable.HashMap[String,Int]()
  
  def receiveUpdate(button: ObservableButton) = {
    val count = clicks.getOrElse(button.label, 0) + 1
    clicks.update(button.label, count)
  }
}
