// code-examples/AppDesign/abstractions/button-observer3-spec.scala

package ui
import org.scalatest.{ FunSpec, ShouldMatchers } 

import observer._

class ButtonObserver3Spec extends FunSpec with ShouldMatchers {
  describe "An Observer counting button clicks" {
    it "see all clicks" {
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
      buttonObserver.clicks("button1") mustEqual 1
      buttonObserver.clicks("button2") mustEqual 2
      buttonObserver.clicks("button3") mustEqual 3
    }
  }

  def clickButton(button: Button, nClicks: Int) = 
    for (i <- 1 to nClicks)
      button.click()
}
