// code-examples/BasicOOP/ui/radio-button-callbacks-spec.scala
package ui

import org.specs2.mutable._ 

object RadioButtonWithCallbacksSpec extends Specification { 
    "A RadioButtonWithCallbacks construction" should { 
        "accept an on/off state and a label" in { 
            val button = new RadioButtonWithCallbacks(true, "radio button1")
            button.label mustEqual "radio button1"
            button.on    mustEqual true
            button.clickedCallbacks.isEmpty mustEqual true
            val button2 = new RadioButtonWithCallbacks(false, "radio button2")
            button2.on mustEqual false
        } 
        "accept an on/off state, a label and 1 click callback" in { 
            var message = ""
            val button = new RadioButtonWithCallbacks(
                true, "radio button1", () => message = "clicked!")
            button.clickedCallbacks.length mustEqual 1
            button.click
            message mustEqual "clicked!"
        } 
        "accept an on/off state, a label and a list of click callbacks" in { 
            var message = ""
            val button = new RadioButtonWithCallbacks(true, "radio button1", 
                List(() => message = "clicked!", 
                () => message += " clicked again!"))
            button.clickedCallbacks.length mustEqual 2
            button.click
            message mustEqual "clicked! clicked again!"
        } 
        "accept a null callback list" in { 
            val nullList:List[() => Unit] = null
            (new RadioButtonWithCallbacks(
                true, "radio button1", nullList)) must throwA(
                    new IllegalArgumentException(
                        "requirement failed: Callback list can't be null!"))
        } 
    } 
} 
