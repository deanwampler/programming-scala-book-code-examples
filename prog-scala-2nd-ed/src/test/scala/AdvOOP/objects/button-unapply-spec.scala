// code-examples/AdvOOP/objects/button-unapply-spec.scala

package objects
import org.scalatest.{ FunSpec, ShouldMatchers } 

class ButtonUnapplySpec extends FunSpec with ShouldMatchers {
  describe ("Button.unapply") {
    it ("match a Button object") {
      val b = new Button("click me")
      b match {
        case Button(label) => label mustEqual "click me"
        case _ => fail()
      }
    }
    it ("match a RadioButton object") {
      val b = new RadioButton(false, "click me")
      b match {
        case Button(label) => label mustEqual "click me"
        case _ => fail()
      }
    }
    it ("not match a non-Button object") {
      val tf = new TextField("hello world!")
      tf match {
        case Button(label) => fail()
        case x => x must notBeNull // hack to make Specs not ignore this test.
      }
    }
    it ("extract the Button's label") {
      val b = new Button("click me")
      b match {
        case Button(label) => label mustEqual "click me"
        case _ => fail()
      }
    }
    it ("extract the RadioButton's label") {
      val rb = new RadioButton(false, "click me, too")
      rb match {
        case Button(label) => label mustEqual "click me, too"
        case _ => fail()
      }
    }
  }
}
