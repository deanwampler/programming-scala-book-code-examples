// src/test/scala/ObjectSystem/objects/widget-apply-spec.scala

package oop.objects

import org.scalatest.{ FunSpec, ShouldMatchers } 

class WidgetApplySpec extends FunSpec with ShouldMatchers {
  describe ("Widget.apply with a valid widget specification string") {
    it ("returns a widget instance with the correct fields set") {
      Widget("(button: label=click me, (Widget))") match {
        case Some(Button("click me")) => // success
        case x => fail(x.toString())
      }
      Widget("(textfield: text=This is text, (Widget))") match {
        case Some(TextField("This is text")) => // success
        case x => fail(x.toString())
      }
    }
  }
  describe ("Widget.apply with an invalid specification string") {
    it ("returns None") {
      Widget("(button: , (Widget)") shouldEqual None
    }
  }
}
