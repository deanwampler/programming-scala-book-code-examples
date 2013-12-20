// code-examples/Traits/ui/button-observer-anon-spec.scala

package ui
import org.specs2.mutable._
import observer._

object ButtonObserverAnonSpec extends Specification {
  "A Button Observer" should {
    "observe button clicks" in {
      val observableButton = new Button("Okay") with Subject {
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
}
