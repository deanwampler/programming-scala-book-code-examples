// code-examples/Traits/ui/button-observer-spec.scala

package ui
import org.specs2.mutable._
import observer._

object ButtonObserverSpec extends Specification {
  "A Button Observer" should {
    "observe button clicks" in {
      val observableButton = new ObservableButton("Okay")
      val buttonObserver = new ButtonCountObserver
      observableButton.addObserver(buttonObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonObserver.count mustEqual 3
    }
  }
}
