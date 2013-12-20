// code-examples/AppDesign/abstractions/button-click-observer3.scala

package appdesign.abstrations
import appdesign.abstractions.observer._
import appdesign.abstractions.ButtonSubjectObserver._
import traits.ui.Button

class ButtonClickObserver extends ButtonObserver {
  val clicks = new scala.collection.mutable.HashMap[String,Int]()
  
  def receiveUpdate(button: ObservableButton) = {
    val count = clicks.getOrElse(button.label, 0) + 1
    clicks.update(button.label, count)
  }
}
