// code-examples/AppDesign/abstractions/button-observer.scala

package appdesign.abstractions
import appdesign.abstractions.observer._
import traits.ui._

object ButtonSubjectObserver extends SubjectObserver {
  type S = ObservableButton
  type O = ButtonObserver
  
  class ObservableButton(name: String) extends Button(name) with Subject {
    override def click() = {
      super.click()
      notifyObservers
    }
  }
  
  trait ButtonObserver extends Observer {
    def receiveUpdate(button: ObservableButton)
  }
}
