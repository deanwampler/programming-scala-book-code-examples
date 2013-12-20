// code-examples/Traits/ui2/button-vetoable-clickable-observer-spec.scala

package ui2
import org.specs2.mutable._
import observer._
import ui.ButtonCountObserver

object ButtonVetoableClickableObserverSpec extends Specification {
  "A Vetoable Button with Click Observer" should {
    "observe all the button clicks, even when some are vetoed" in {
      val observableButton = 
          new Button("Okay") with VetoableClicks with ObservableClicks
      val buttonClickCountObserver = new ButtonCountObserver
      observableButton.addObserver(buttonClickCountObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonClickCountObserver.count mustEqual 3
    }
  }
}
