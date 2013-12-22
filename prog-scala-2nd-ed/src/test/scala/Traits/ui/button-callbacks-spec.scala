// code-examples/Traits/ui/button-callbacks-spec.scala
package ui
import org.scalatest.{ FunSpec, ShouldMatchers } 

class ButtonWithCallbacksSpec extends FunSpec with ShouldMatchers { 
  describe ("A ButtonWithCallbacks") { 
    it ("be constructable with just a label") { 
      val button = new ButtonWithCallbacks("button1")
      button.label mustEqual "button1"
      button.clickedCallbacks.isEmpty mustEqual true
    } 
    it ("be constructable with a label and one click callback") {
      var message = ""
      val button = new ButtonWithCallbacks(
              "button1", () => message = "clicked!")
      button.clickedCallbacks.length mustEqual 1
      button.click
      message mustEqual "clicked!"
    } 
    it ("be constructable with a label and a list of click callbacks") {
      var message = ""
      val button = new ButtonWithCallbacks("button1", 
          List(() => message  = "clicked!", 
               () => message += " clicked again!"))
      button.clickedCallbacks.length mustEqual 2
      button.click
      message mustEqual "clicked! clicked again!"
    } 
    it ("not be constructable with a null callback list") { 
      val nullList:List[() => Unit] = null
      val errorMessage = 
        "requirement failed: Callback list can't be null!"
      (new ButtonWithCallbacks("button1", nullList)) must throwA(
        new IllegalArgumentException(errorMessage)) 
    } 
  } 
} 
