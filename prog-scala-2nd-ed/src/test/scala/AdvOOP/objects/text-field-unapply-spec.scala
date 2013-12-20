// code-examples/AdvOOP/objects/text-field-unapply-spec.scala
package objects
import org.specs2.mutable._

object TextFieldUnapplySpec extends Specification {
    "TextField.unapply" should {
        "should match a TextField object" in {
            val tf = new TextField("hello world!")
            tf match {
                case TextField(text) => text mustEqual "hello world!"
                case _ => fail()
            }
        }
        "not match a non-TextField object" in {
            val b = new Button("click me")
            b match {
                case TextField(text) => fail()
                case x => x must notBeNull
            }
        }
        "extract the text field's text" in {
            val tf = new TextField("hello world!")
            tf match {
                case TextField(text) => text mustEqual "hello world!"
                case _ => fail()
            }
        }
    }
}
