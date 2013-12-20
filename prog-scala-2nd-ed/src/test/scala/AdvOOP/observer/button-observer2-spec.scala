// code-examples/AdvOOP/observer/button-observer2-spec.scala

package ui
import org.specs2.mutable._
import observer._

object ButtonObserver2Spec extends Specification {
  "An Observer watching a SubjectForReceiveUpdateObservers button" should {
    "observe button clicks" in {
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
  "An Observer watching a SubjectForFunctionalObservers button" should {
    "observe button clicks" in {
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
