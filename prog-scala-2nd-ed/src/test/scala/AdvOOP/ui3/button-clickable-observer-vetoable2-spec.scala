// code-examples/AdvOOP/ui3/button-clickable-observer-vetoable2-spec.scala
package ui3

import org.specs2.mutable._
import observer._
import ui.ButtonCountObserver

object ButtonClickableObserverVetoableSpec2 extends Specification {
  "A Button Observer with Vetoable Clicks" should {
    "observe only the first 'maxAllowed' clicks" in {
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
