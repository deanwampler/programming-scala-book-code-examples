// src/test/scala/progscala3/metaprogramming/RequireSuite.scala
package progscala3.metaprogramming

import scala.language.implicitConversions
import munit._

class RequireSuite extends FunSuite {

  val list = List(1,2,3)

  test("A true predicate passes") {
    require(list.size == 3)
  }

  test("A false predicate fails") {
    intercept[RequirementFailure] {
      require(list.size != 3)
    }
  }

  test("A false predicate fails and prints a provided additional message") {
    try { require(list.size != 3, "Wrong size!") }
    catch {
      case RequirementFailure(_, note) =>
        println(s"note: $note")
        require(note == "\"Wrong size!\"")
    }
  }
}
