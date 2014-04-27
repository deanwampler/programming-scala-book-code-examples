// src/test/scala/ObjectSystem/objects/button-unapply-spec.scala

package oop.objects

import org.scalatest.{ FunSpec, ShouldMatchers } 

class ButtonUnapplySpec extends FunSpec with ShouldMatchers {
  describe ("Button.unapply") {
    it ("matches a Button object") {
      val b: Widget = new Button("click me")
      b match {
        case Button("click me") => // correct
        case Button(label) => fail(s"It's a button, but the label did not match 'click me': $label")
        case _ => fail()
      }
    }
    it ("matches a RadioButton object") {
      val b: Widget = new RadioButton(false, "click me")
      b match {
        case Button("click me") => // correct
        case _ => fail()
      }
    }
    it ("does not match a non-Button object") {
      val tf: Widget = new TextField("hello world!")
      tf match {
        case Button(label) => fail()
        case TextField(label) => // success
        case x => fail()
      }
    }
    it ("extracts the Button's label") {
      val b: Widget = new Button("click me")
      b match {
        case Button(label) => label shouldEqual "click me"
        case _ => fail()
      }
    }
    it ("extracts the RadioButton's label") {
      val rb: Widget = new RadioButton(false, "click me, too")
      rb match {
        case Button(label) => label shouldEqual "click me, too"
        case _ => fail()
      }
    }
  }
}
