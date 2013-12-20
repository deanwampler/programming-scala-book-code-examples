// code-examples/Traits/ui/observable-button.scala

package traits.ui
import traits.observer._

class ObservableButton(name: String) extends Button(name) with Subject {
  override def click() = {
    super.click()
    notifyObservers
  }
}
