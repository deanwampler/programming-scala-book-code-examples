// code-examples/AdvOOP/objects/radio-button-unapply-spec.scala

package objects
import org.scalatest.{ FunSpec, ShouldMatchers }

class RadioButtonUnapplySpec extends FunSpec with ShouldMatchers {
  describe ("RadioButton.unapply") {
    it ("should match a RadioButton object") {
      val b = new RadioButton(true, "click me")
      b match {
        case RadioButton(on, label) => label mustEqual "click me"
        case _ => fail()
      }
    }
    it ("not match a Button (parent class) object") {
      val b = new Button("click me")
      b match {
        case RadioButton(on, label) => fail()
        case x => x must notBeNull
      }
    }
    it ("not match a non-RadioButton object") {
      val tf = new TextField("hello world!")
      tf match {
        case RadioButton(on, label) => fail()
        case x => x must notBeNull
      }
    }
    it ("extract the RadioButton's on/off state and label") {
      val b = new RadioButton(true, "click me")
      b match {
        case RadioButton(on, label) => {
          label mustEqual "click me"
          on    mustEqual true
        }
        case _ => fail()
      }
    }
  }
}
