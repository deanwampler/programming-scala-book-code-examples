// code-examples/Traits/ui2/button-clickable-observer-vetoable-spec.scala

package ui2
import org.scalatest.{ FunSpec, ShouldMatchers }
import observer._
import ui.ButtonCountObserver

class ButtonClickableObserverVetoableSpec extends FunSpec with ShouldMatchers {
  describe ("A Button Observer with Vetoable Clicks") {
    it ("observe only the first button click") {
      val observableButton = 
          new Button("Okay") with ObservableClicks with VetoableClicks
      val buttonClickCountObserver = new ButtonCountObserver
      observableButton.addObserver(buttonClickCountObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonClickCountObserver.count mustEqual 1
    }
  }
}
