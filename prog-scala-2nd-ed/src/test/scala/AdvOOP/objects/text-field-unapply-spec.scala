// code-examples/AdvOOP/objects/text-field-unapply-spec.scala
package objects
import org.scalatest.{ FunSpec, ShouldMatchers }

class TextFieldUnapplySpec extends FunSpec with ShouldMatchers {
    describe ("TextField.unapply") {
        it ("should match a TextField object") {
            val tf = new TextField("hello world!")
            tf match {
                case TextField(text) => text mustEqual "hello world!"
                case _ => fail()
            }
        }
        it ("not match a non-TextField object") {
            val b = new Button("click me")
            b match {
                case TextField(text) => fail()
                case x => x must notBeNull
            }
        }
        it ("extract the text field's text") {
            val tf = new TextField("hello world!")
            tf match {
                case TextField(text) => text mustEqual "hello world!"
                case _ => fail()
            }
        }
    }
}
