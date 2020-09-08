// src/test/scala/progscala3/metaprogramming/RequirementSuite.scala
package progscala3.metaprogramming

import munit._

class RequirementSuite extends FunSuite:

  val list = List(1,2,3)

  test("A true predicate passes") {
    requirement(list.size == 3)
  }

  test("A false predicate fails") {
    intercept[RequirementFailure] {
      requirement(list.size != 3)
    }
  }

  test("A false predicate fails and prints a provided additional message") {
    try requirement(list.size != 3, "Wrong size!")
    catch
      case rf: RequirementFailure =>
        assert(rf.message.contains("Wrong size!"))
  }

  test("When you know a requirement has failed, use the fail method") {
    try requirement.fail("Wrong size!")
    catch
      case rf: RequirementFailure =>
        assert(rf.message.contains("Wrong size!"))
  }
