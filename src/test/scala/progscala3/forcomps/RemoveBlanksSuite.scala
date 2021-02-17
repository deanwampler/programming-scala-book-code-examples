// src/test/scala/progscala3/forcomps/RemoveBlanksSuite.scala
package progscala3.forcomps

import munit.*

class RemoveBlanksSuite extends FunSuite:
  var path = "src/test/scala/progscala3/forcomps/small-test-file.txt"

  test("RemoveBlanks removes blank lines in text") {
    val lines = RemoveBlanks(path, compress = false, numbers = false)
    assert(lines.mkString("\n") ==
      """  This is a       small
        |test   file""".stripMargin)
  }

  test("RemoveBlanks optionally compresses whitespace in text") {
    val lines = RemoveBlanks(path, compress = true, numbers = false)
    assert(lines.mkString("\n") ==
      """This is a small
        |test file""".stripMargin)
  }

  test("RemoveBlanks optionally prints line numbers from the original text") {
    val lines = RemoveBlanks(path, compress = true, numbers = true)
    assert(lines.mkString("\n") ==
      """   1: This is a small
        |   3: test file""".stripMargin, lines.mkString("\n"))
  }
