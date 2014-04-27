// src/test/scala/ObjectSystem/objects/text-field-unapply-spec.scala

package oop.objects

import org.scalatest.{ FunSpec, ShouldMatchers } 

class TextFieldUnapplySpec extends FunSpec with ShouldMatchers {
  describe ("TextField.unapply") {
    it ("matches a TextField object") {
      val tf: Widget = new TextField("hello world!")
      tf match {
        case TextField("hello world!") => // success
        case _ => fail()
      }
    }
    it ("does not match a non-TextField object") {
      val b: Widget = new Button("click me")
      b match {
        case TextField(text) => fail()
        case Button(label) => // success
      }
    }
    it ("extracts the text field's text") {
      val tf: Widget = new TextField("hello world!")
      tf match {
        case TextField(text) => text shouldEqual "hello world!"
        case _ => fail()
      }
    }
  }
}
