// code-examples/BasicOOP/ui/radio-button-callbacks-spec.scala
package ui

import org.scalatest.{ FunSpec, ShouldMatchers } 

class RadioButtonWithCallbacksSpec extends FunSpec with ShouldMatchers { 
    describe ("A RadioButtonWithCallbacks construction") { 
        it ("accept an on/off state and a label") { 
            val button = new RadioButtonWithCallbacks(true, "radio button1")
            button.label mustEqual "radio button1"
            button.on    mustEqual true
            button.clickedCallbacks.isEmpty mustEqual true
            val button2 = new RadioButtonWithCallbacks(false, "radio button2")
            button2.on mustEqual false
        } 
        it ("accept an on/off state, a label and 1 click callback") { 
            var message = ""
            val button = new RadioButtonWithCallbacks(
                true, "radio button1", () => message = "clicked!")
            button.clickedCallbacks.length mustEqual 1
            button.click
            message mustEqual "clicked!"
        } 
        it ("accept an on/off state, a label and a list of click callbacks") { 
            var message = ""
            val button = new RadioButtonWithCallbacks(true, "radio button1", 
                List(() => message = "clicked!", 
                () => message += " clicked again!"))
            button.clickedCallbacks.length mustEqual 2
            button.click
            message mustEqual "clicked! clicked again!"
        } 
        it ("accept a null callback list") { 
            val nullList:List[() => Unit] = null
            (new RadioButtonWithCallbacks(
                true, "radio button1", nullList)) must throwA(
                    new IllegalArgumentException(
                        "requirement failed: Callback list can't be null!"))
        } 
    } 
} 
