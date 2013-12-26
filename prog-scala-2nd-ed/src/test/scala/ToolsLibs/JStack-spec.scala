// code-examples/ToolsLibs/JStack-spec.scala

import org.scalatest.{ FunSpec, ShouldMatchers }  

class JStackSpec extends FunSpec with ShouldMatchers {
  describe ("Calling a Java generic type from Scala") {
    it ("supports Java-style generic parameterization") {
      val js = new JStack[String]
      js shouldNot be (null)  // Dummy check...
    }
    it ("supports invoking the Java type's methods") {
      val js = new JStack[String]
      js.push("one")
      js.push("two")
      js.pop() shouldEqual "two"
      js.pop() shouldEqual "one"
    }
  }
}
