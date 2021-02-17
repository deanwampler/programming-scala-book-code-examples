// src/test/scala/progscala3/typelessdomore/AbstractTypesSuite.scala
package progscala3.typelessdomore

import munit.*
import scala.io.Source

class AbstractTypesSuite extends FunSuite:

  test("BulkReader for Strings") {
    val result = StringBulkReader("Hello Scala!").read
    assert (result.head == "Hello Scala!")
  }

  test("BulkReader for Files") {
    val source = Source.fromFile("src/test/scala/progscala3/typelessdomore/AbstractTypesSuite.scala")
    val result = FileBulkReader(source).read
    assert (result(1) == "package progscala3.typelessdomore")
    assert (result(3) == "import munit.*")
  }
