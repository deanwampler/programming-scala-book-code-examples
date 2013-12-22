// code-examples/AdvOOP/ui3/button-clickable-observer-vetoable2-spec.scala
package ui3

import org.scalatest.{ FunSpec, ShouldMatchers }
import observer._
import ui.ButtonCountObserver

class ButtonClickableObserverVetoableSpec2 extends FunSpec with ShouldMatchers {
  describe ("A Button Observer with Vetoable Clicks") {
    it ("observe only the first 'maxAllowed' clicks") {
      val observableButton = 
        new Button("Okay") with ObservableClicks with VetoableClicks {
          maxAllowed = 2
      }
      observableButton.maxAllowed mustEqual 2
      val buttonClickCountObserver = new ButtonCountObserver
      observableButton.addObserver(buttonClickCountObserver)
      for (i <- 1 to 3) observableButton.click()
      buttonClickCountObserver.count mustEqual 2
    }
  }
}
