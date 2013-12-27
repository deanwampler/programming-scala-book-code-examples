// src/test/scala/Traits/ui/button-observer-anon-spec.scala

package traits.ui
import org.scalatest.{ FunSpec, ShouldMatchers }
import traits.observer._

class ButtonObserverAnonSpec extends FunSpec with ShouldMatchers {
  describe ("A Button Observer") {
    it ("observe button clicks") {
      val observableButton = new Button("Okay") with Subject {
        override def click() = {
          super.click()
          notifyObservers
        }
      }

      val buttonObserver = new ButtonCountObserver
      observableButton.addObserver(buttonObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonObserver.count shouldEqual 3
    }
  }
}
