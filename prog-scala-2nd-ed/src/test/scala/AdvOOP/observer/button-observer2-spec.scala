// code-examples/AdvOOP/observer/button-observer2-spec.scala

package ui
import org.scalatest.{ FunSpec, ShouldMatchers }
import observer._

class ButtonObserver2Spec extends FunSpec with ShouldMatchers {
  describe ("An Observer watching a SubjectForReceiveUpdateObservers button") {
    it ("observe button clicks") {
      val observableButton = 
        new Button(name) with SubjectForReceiveUpdateObservers {
        override def click() = { 
          super.click()
          notifyObservers
        }
      }
      val buttonObserver = new ButtonCountObserver
      observableButton.addObserver(buttonObserver)
      for (i <- 1 to 3) observableButton.click()
      buttonObserver.count mustEqual 3
    }
  }
  describe ("An Observer watching a SubjectForFunctionalObservers button") {
    it ("observe button clicks") {
      val observableButton = 
        new Button(name) with SubjectForFunctionalObservers {
        override def click() = { 
          super.click()
          notifyObservers
        }
      }
      var count = 0
      observableButton.addObserver((button) => count += 1)
      for (i <- 1 to 3) observableButton.click()
      count mustEqual 3
    }
  }
}
