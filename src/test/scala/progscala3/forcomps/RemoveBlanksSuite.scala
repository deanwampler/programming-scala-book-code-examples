// src/test/scala/progscala3/forcomps/RemoveBlanksSuite.scala
package progscala3.forcomps

import munit.*

class RemoveBlanksSuite extends FunSuite:
  var path = "src/test/scala/progscala3/forcomps/small-test-file.txt"

  test("RemoveBlanks removes blank lines in text") {
    val lines = RemoveBlanks(path, compress = false, numbers = false)
    val actual = lines.mkString("\n")
    val expected = 
      """  This is a       small
        |test   file""".stripMargin
    assert(actual == expected, s"""Actual:\n$actual\nExpected:\n$expected\n""")
  }

  test("RemoveBlanks optionally compresses whitespace in text") {
    val lines = RemoveBlanks(path, compress = true, numbers = false)
    val actual = lines.mkString("\n")
    val expected = 
      """This is a small
        |test file""".stripMargin
    assert(actual == expected, s"""Actual:\n$actual\nExpected:\n$expected\n""")
  }

  test("RemoveBlanks optionally prints line numbers from the original text") {
    val lines = RemoveBlanks(path, compress = true, numbers = true)
    val actual = lines.mkString("\n")
    val expected = 
      """   1: This is a small
        |   3: test file""".stripMargin
    assert(actual == expected, s"""Actual:\n$actual\nExpected:\n$expected\n""")
  }
