// code-examples/Traits/ui/button-callbacks-spec.scala
package ui
import org.specs2.mutable._ 

object ButtonWithCallbacksSpec extends Specification { 
  "A ButtonWithCallbacks" should { 
    "be constructable with just a label" in { 
      val button = new ButtonWithCallbacks("button1")
      button.label mustEqual "button1"
      button.clickedCallbacks.isEmpty mustEqual true
    } 
    "be constructable with a label and one click callback" in {
      var message = ""
      val button = new ButtonWithCallbacks(
              "button1", () => message = "clicked!")
      button.clickedCallbacks.length mustEqual 1
      button.click
      message mustEqual "clicked!"
    } 
    "be constructable with a label and a list of click callbacks" in {
      var message = ""
      val button = new ButtonWithCallbacks("button1", 
          List(() => message  = "clicked!", 
               () => message += " clicked again!"))
      button.clickedCallbacks.length mustEqual 2
      button.click
      message mustEqual "clicked! clicked again!"
    } 
    "not be constructable with a null callback list" in { 
      val nullList:List[() => Unit] = null
      val errorMessage = 
        "requirement failed: Callback list can't be null!"
      (new ButtonWithCallbacks("button1", nullList)) must throwA(
        new IllegalArgumentException(errorMessage)) 
    } 
  } 
} 
