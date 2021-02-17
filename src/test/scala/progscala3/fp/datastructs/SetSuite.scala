// src/test/scala/progscala3/fp/datastructs/SetSuite.scala
package progscala3.fp.datastructs

import munit.*

class SetSuite extends FunSuite:

  val states = Set("Alabama", "Alaska", "Wyoming")

  test("Sets have unique elements") {
    val lengths = states map (st => st.length)
    assert(lengths == Set(6, 7))  // two names are 7 characters long
  }

  test("Sets can be mapped") {
    val lengths = states map (st => st.length)
    assert(lengths == Set(6, 7))
  }

  test("Add to a set with +") {
    val states2 = states + "Virginia"
    assert(states2 == Set("Alabama", "Alaska", "Wyoming", "Virginia"))
  }

  test("Join two sets with ++") {
    val states2 = states ++ Seq("New York", "Illinois", "Alaska")  // <1>
    assert(states2 ==
      Set("Alabama", "Alaska", "Wyoming", "New York", "Illinois"))
  }
