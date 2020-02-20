// src/test/scala/progscala3/forcomps/RemoveBlanksSuite.scala
package progscala3.forcomps

import munit._
import progscala3.metaprogramming.requirement

class RemoveBlanksSuite extends FunSuite {

  var selfPath = "src/test/scala/progscala3/forcomps/small-test-file.txt"

  test("RemoveBlanks removes blank lines in text") {
    val lines = RemoveBlanks(selfPath, compress = false)
    requirement(lines == """
      |This is a       small
      |test   file
      |""".stripMargin)
  }

  test("RemoveBlanks optionally compresses whitespace in text") {
    val lines = RemoveBlanks(selfPath, compress = true)
    requirement(lines == """
      |This is a small
      |test file
      |""".stripMargin)
  }
}
