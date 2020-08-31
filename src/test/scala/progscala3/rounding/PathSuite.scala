// src/test/scala/progscala3/rounding/PathSuite.scala
package progscala3.rounding

import munit._
import java.io.File

class PathSuite extends FunSuite:

  def doTest(separator: String) =
    val one  = Path("one", separator = separator)
    val three = one / "two" / "three"
    val expected = s"one${separator}two${separator}three"
    assert(expected.equals(three.value))
    assert(expected.equals(three.toString))
    assert(new File(expected).equals(three.file))

  test("concatenation with the default separator") {
    doTest(Path.defaultSeparator)
  }
  test("concatenation with a user-specified separator") {
    doTest("|")
  }
