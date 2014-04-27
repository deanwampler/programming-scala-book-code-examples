// src/test/scala/ObjectSystem/objects/radio-button-unapply-spec.scala

package oop.objects

import org.scalatest.{ FunSpec, ShouldMatchers } 

class RadioButtonUnapplySpec extends FunSpec with ShouldMatchers {
  describe ("RadioButton.unapply") {
    it ("matches a RadioButton object") {
      val b: Widget = new RadioButton(true, "click me")
      b match {
        case RadioButton(true, "click me") => // success
        case _ => fail()
      }
    }
    it ("does not match a Button (parent class) object") {
      val b: Widget = new Button("click me")
      b match {
        case RadioButton(on, label) => fail()
        case Button(label) => // success
        case x => fail(s"$x is not a Button!")
      }
    }
    it ("does not match a non-RadioButton object") {
      val tf: Widget = new TextField("hello world!")
      tf match {
        case RadioButton(on, label) => fail()
        case TextField(label) => // success
        case x => fail()
      }
    }
    it ("extracts the RadioButton's on/off state and label") {
      val b: Widget = new RadioButton(true, "click me")
      b match {
        case RadioButton(on, label) => {
          label shouldEqual "click me"
          on    shouldEqual true
        }
        case _ => fail()
      }
    }
  }
}
