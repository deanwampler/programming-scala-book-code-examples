// src/test/scala/Traits/ui/button-callbacks-spec.scala
package traits.ui
import org.scalatest.{ FunSpec, ShouldMatchers } 

class ButtonWithCallbacksSpec extends FunSpec with ShouldMatchers { 
  describe ("A ButtonWithCallbacks") { 
    it ("be constructable with just a label") { 
      val button = new ButtonWithCallbacks("button1")
      button.label shouldEqual "button1"
      button.clickedCallbacks.isEmpty shouldEqual true
    } 
    it ("be constructable with a label and one click callback") {
      var message = ""
      val button = new ButtonWithCallbacks(
              "button1", () => message = "clicked!")
      button.clickedCallbacks.length shouldEqual 1
      button.click
      message shouldEqual "clicked!"
    } 
    it ("be constructable with a label and a list of click callbacks") {
      var message = ""
      val button = new ButtonWithCallbacks("button1", 
          List(() => message  = "clicked!", 
               () => message += " clicked again!"))
      button.clickedCallbacks.length shouldEqual 2
      button.click
      message shouldEqual "clicked! clicked again!"
    } 
    it ("not be constructable with a null callback list") { 
      val nullList:List[() => Unit] = null
      intercept[IllegalArgumentException] {
        new ButtonWithCallbacks("button1", nullList)
      }
    } 
  } 
} 
