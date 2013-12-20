// code-examples/AdvOOP/objects/button-unapply-spec.scala

package objects
import org.specs2.mutable._

object ButtonUnapplySpec extends Specification {
  "Button.unapply" should {
    "match a Button object" in {
      val b = new Button("click me")
      b match {
        case Button(label) => label mustEqual "click me"
        case _ => fail()
      }
    }
    "match a RadioButton object" in {
      val b = new RadioButton(false, "click me")
      b match {
        case Button(label) => label mustEqual "click me"
        case _ => fail()
      }
    }
    "not match a non-Button object" in {
      val tf = new TextField("hello world!")
      tf match {
        case Button(label) => fail()
        case x => x must notBeNull // hack to make Specs not ignore this test.
      }
    }
    "extract the Button's label" in {
      val b = new Button("click me")
      b match {
        case Button(label) => label mustEqual "click me"
        case _ => fail()
      }
    }
    "extract the RadioButton's label" in {
      val rb = new RadioButton(false, "click me, too")
      rb match {
        case Button(label) => label mustEqual "click me, too"
        case _ => fail()
      }
    }
  }
}
