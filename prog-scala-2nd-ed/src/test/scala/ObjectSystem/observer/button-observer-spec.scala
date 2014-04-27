// src/test/scala/ObjectSystem/observer/button-observer-spec.scala

package oop.ui

import advoop.observer._
import traits.ui.ButtonCountObserver
import org.scalatest.{ FunSpec, ShouldMatchers } 

class ButtonObserverSpec extends FunSpec with ShouldMatchers {
  describe ("An Observer watching a SubjectForReceiveUpdateObservers button") {
    it ("observes button clicks") {
      val observableButton = 
        new Button("button") with SubjectForReceiveUpdateObservers {
        override def click() = { 
          super.click()
          notifyObservers
        }
      }
      val buttonObserver = new ButtonCountObserver
      observableButton.addObserver(buttonObserver)
      for (i <- 1 to 3) observableButton.click()
      buttonObserver.count shouldEqual 3
    }
  }
  describe ("An Observer watching a SubjectForFunctionalObservers button") {
    it ("observes button clicks") {
      val observableButton = 
        new Button("button") with SubjectForFunctionalObservers {
        override def click() = { 
          super.click()
          notifyObservers
        }
      }
      var count = 0
      observableButton.addObserver((button) => count += 1)
      for (i <- 1 to 3) observableButton.click()
      count shouldEqual 3
    }
  }
}
