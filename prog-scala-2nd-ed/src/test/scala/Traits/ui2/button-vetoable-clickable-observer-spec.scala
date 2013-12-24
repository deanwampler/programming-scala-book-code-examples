// code-examples/Traits/ui2/button-vetoable-clickable-observer-spec.scala

package traits.ui2
import org.scalatest.{ FunSpec, ShouldMatchers }
import traits.ui.ButtonCountObserver

class ButtonVetoableClickableObserverSpec extends FunSpec with ShouldMatchers {
  describe ("A Vetoable Button with Click Observer") {
    it ("observe all the button clicks, even when some are vetoed") {
      val observableButton = 
          new Button("Okay") with VetoableClicks with ObservableClicks
      val buttonClickCountObserver = new ButtonCountObserver
      observableButton.addObserver(buttonClickCountObserver)

      for (i <- 1 to 3) observableButton.click()
      buttonClickCountObserver.count shouldEqual 3
    }
  }
}
