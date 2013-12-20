// code-examples/ToolsLibs/JStack-spec.scala

import org.specs2.mutable._ 

object JStackSpec extends Specification {
  "Calling a Java generic type from Scala" should {
    "Support parameterization" in {
      val js = new JStack[String]
      js must notBe(null)  // Dummy check...
    }
    "Support invoking the the type's methods" in {
      val js = new JStack[String]
      js.push("one")
      js.push("two")
      js.pop() mustEqual "two"
      js.pop() mustEqual "one"
    }
  }
}
