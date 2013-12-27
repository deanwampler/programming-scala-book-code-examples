// src/test/scala/Traits/ui2/button-clickable-observer-vetoable2-spec.scala

package traits.ui2
import org.scalatest.{ FunSpec, ShouldMatchers }
import traits.ui.ButtonCountObserver

class ButtonClickableObserverVetoableSpec extends FunSpec with ShouldMatchers {
  describe ("A Button Observer with Vetoable Clicks") {
    it ("observe only the first button click") {
      val observableButton = 
          new Button("Okay") with ObservableClicks with VetoableClicks
      val buttonClickCountObserver = new ButtonCountObserver
      observableButton.addObserver(buttonClickCountObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonClickCountObserver.count shouldEqual 1
    }
  }
}
