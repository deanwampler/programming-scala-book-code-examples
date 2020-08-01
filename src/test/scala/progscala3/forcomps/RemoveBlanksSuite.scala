// src/test/scala/progscala3/forcomps/RemoveBlanksSuite.scala
package progscala3.forcomps

import munit._

class RemoveBlanksSuite extends FunSuite:
  var path = "src/test/scala/progscala3/forcomps/small-test-file.txt"

  test("RemoveBlanks removes blank lines in text") {
    val lines = RemoveBlanks(path, compressSpace = false)
    assert(lines.mkString("\n") ==
      """  This is a       small
        |test   file""".stripMargin)
  }

  test("RemoveBlanks optionally compresses whitespace in text") {
    val lines = RemoveBlanks(path, compressSpace = true)
    assert(lines.mkString("\n") ==
      """This is a small
        |test file""".stripMargin)
  }
