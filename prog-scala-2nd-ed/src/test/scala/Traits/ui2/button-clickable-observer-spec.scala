// code-examples/Traits/ui2/button-clickable-observer-spec.scala

package ui2
import org.specs2.mutable._
import observer._
import ui.ButtonCountObserver

object ButtonClickableObserverSpec extends Specification {
  "A Button Observer" should {
    "observe button clicks" in {
      val observableButton = new Button("Okay") with ObservableClicks
      val buttonClickCountObserver = new ButtonCountObserver
      observableButton.addObserver(buttonClickCountObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonClickCountObserver.count mustEqual 3
    }
  }
}
