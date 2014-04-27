// src/test/scala/ObjectSystem/ui/button-clickable-observer-vetoable-spec.scala

package oop.ui

import advoop.observer._
import traits.ui.ButtonCountObserver
import org.scalatest.{ FunSpec, ShouldMatchers } 

class ButtonClickableObserverVetoableSpec extends FunSpec with ShouldMatchers {
  describe ("A Button Observer with Vetoable Clicks") {
    it ("observes only the first 'maxAllowed' clicks") {
      val observableButton = 
        new Button("Okay") with ObservableClicks with VetoableClicks {
          maxAllowed = 2
      }
      observableButton.maxAllowed shouldEqual 2
      val buttonClickCountObserver = new ButtonCountObserver
      observableButton.addObserver(buttonClickCountObserver)
      for (i <- 1 to 3) observableButton.click()
      buttonClickCountObserver.count shouldEqual 2
    }
  }
}
