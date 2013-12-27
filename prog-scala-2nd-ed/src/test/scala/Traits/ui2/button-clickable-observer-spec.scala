// src/test/scala/Traits/ui2/button-clickable-observer-spec.scala

package traits.ui2
import org.scalatest.{ FunSpec, ShouldMatchers }
import traits.ui.ButtonCountObserver

class ButtonClickableObserverSpec extends FunSpec with ShouldMatchers {
  describe ("A Button Observer") {
    it ("observe button clicks") {
      val observableButton = new Button("Okay") with ObservableClicks
      val buttonClickCountObserver = new ButtonCountObserver
      observableButton.addObserver(buttonClickCountObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonClickCountObserver.count shouldEqual 3
    }
  }
}
