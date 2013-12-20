// code-examples/AdvOOP/objects/radio-button-unapply-spec.scala

package objects
import org.specs2.mutable._

object RadioButtonUnapplySpec extends Specification {
  "RadioButton.unapply" should {
    "should match a RadioButton object" in {
      val b = new RadioButton(true, "click me")
      b match {
        case RadioButton(on, label) => label mustEqual "click me"
        case _ => fail()
      }
    }
    "not match a Button (parent class) object" in {
      val b = new Button("click me")
      b match {
        case RadioButton(on, label) => fail()
        case x => x must notBeNull
      }
    }
    "not match a non-RadioButton object" in {
      val tf = new TextField("hello world!")
      tf match {
        case RadioButton(on, label) => fail()
        case x => x must notBeNull
      }
    }
    "extract the RadioButton's on/off state and label" in {
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
