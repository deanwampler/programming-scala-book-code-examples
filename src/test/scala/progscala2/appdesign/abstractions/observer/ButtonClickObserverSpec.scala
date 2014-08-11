// src/test/scala/progscala2/appdesign/abstractions/observer/ButtonClickObserverSpec.scala

package progscala2.appdesign.abstractions.observer

import progscala2.traits.ui._
import org.scalatest.{ FunSpec, ShouldMatchers } 

class ButtonClickObserverSpec extends FunSpec with ShouldMatchers {
  describe ("An Observer counting button clicks") {
    it ("see all clicks") {
      val button1 = new ButtonSubjectObserver.ObservableButton("button1")
      val button2 = new ButtonSubjectObserver.ObservableButton("button2")
      val button3 = new ButtonSubjectObserver.ObservableButton("button3")
      val buttonObserver = new ButtonClickObserver
      button1.addObserver(buttonObserver)
      button2.addObserver(buttonObserver)
      button3.addObserver(buttonObserver)
      clickButton(button1, 1)
      clickButton(button2, 2)
      clickButton(button3, 3)
      buttonObserver.clicks("button1") shouldEqual 1
      buttonObserver.clicks("button2") shouldEqual 2
      buttonObserver.clicks("button3") shouldEqual 3
    }
  }

  def clickButton(button: Button, nClicks: Int) = 
    for (i <- 1 to nClicks)
      button.click()
}
