// src/test/scala/Traits/ui/button-observer-spec.scala

package traits.ui
import org.scalatest.{ FunSpec, ShouldMatchers }
import traits.observer._

class ButtonObserverSpec extends FunSpec with ShouldMatchers {
  describe ("A Button Observer") {
    it ("observe button clicks") {
      val observableButton = new ObservableButton("Okay")
      val buttonObserver = new ButtonCountObserver
      observableButton.addObserver(buttonObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonObserver.count shouldEqual 3
    }
  }
}
