// src/main/scala/progscala2/appdesign/abstractions/observer/button-observer.scala

package progscala2.appdesign.abstractions.observer
import progscala2.traits.ui._

object ButtonSubjectObserver extends SubjectObserver {
  type S = ObservableButton
  type O = ButtonObserver
  
  class ObservableButton(name: String) extends Button(name) with Subject {
    override def click() = {
      super.click()
      notifyObservers()
    }
  }
  
  trait ButtonObserver extends Observer {
    def receiveUpdate(button: ObservableButton)
  }
}
