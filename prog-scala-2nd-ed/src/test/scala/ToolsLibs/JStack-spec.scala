// code-examples/ToolsLibs/JStack-spec.scala

import org.scalatest.{ FunSpec, ShouldMatchers } 

class JStackSpec extends FunSpec with ShouldMatchers {
  describe ("Calling a Java generic type from Scala") {
    it ("Support parameterization") {
      val js = new JStack[String]
      js must notBe(null)  // Dummy check...
    }
    it ("Support invoking the the type's methods") {
      val js = new JStack[String]
      js.push("one")
      js.push("two")
      js.pop() mustEqual "two"
      js.pop() mustEqual "one"
    }
  }
}
