// src/test/scala/progscala3/metaprogramming/RequireSuite.scala
package progscala3.metaprogramming

class RequireSuite extends progscala3.FunSuite2 {

  val list = List(1,2,3)

  test("A true predicate passes") {
    require(list.size == 3)
  }

  test("A false predicate fails") {
    intercept2[RequirementFailure] {
      require(list.size != 3)
    }
  }

  test("A false predicate fails and prints a provided additional message") {
    try { require(list.size != 3, "Wrong size!") }
    catch {
      case RequirementFailure(_, note) => require(note == "Wrong size!")
    }
  }
}
