// src/test/scala/BasicOOP/ui/radio-button-callbacks-spec.scala
package oop.ui

import org.scalatest.{ FunSpec, ShouldMatchers } 

class RadioButtonWithCallbacksSpec extends FunSpec with ShouldMatchers { 
    describe ("A RadioButtonWithCallbacks construction") { 
        it ("accept an on/off state and a label") { 
            val button = new RadioButtonWithCallbacks(true, "radio button1")
            button.label shouldEqual "radio button1"
            button.on    shouldEqual true
            button.clickedCallbacks.isEmpty shouldEqual true
            val button2 = new RadioButtonWithCallbacks(false, "radio button2")
            button2.on shouldEqual false
        } 
        it ("accept an on/off state, a label and 1 click callback") { 
            var message = ""
            val button = new RadioButtonWithCallbacks(
                true, "radio button1", () => message = "clicked!")
            button.clickedCallbacks.length shouldEqual 1
            button.click
            message shouldEqual "clicked!"
        } 
        it ("accept an on/off state, a label and a list of click callbacks") { 
            var message = ""
            val button = new RadioButtonWithCallbacks(true, "radio button1", 
                List(() => message = "clicked!", 
                () => message += " clicked again!"))
            button.clickedCallbacks.length shouldEqual 2
            button.click
            message shouldEqual "clicked! clicked again!"
        } 
        it ("accept a null callback list") { 
            val nullList:List[() => Unit] = null
            intercept[IllegalArgumentException] {
                new RadioButtonWithCallbacks(true, "radio button1", nullList)
            }
        } 
    } 
} 
