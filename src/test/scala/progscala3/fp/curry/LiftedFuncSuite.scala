// src/test/scala/progscala3/fp/curry/LiftedFuncSuite.scala
package progscala3.fp.curry

import munit.*

class LiftedFuncSuite extends FunSuite:

  val finicky: PartialFunction[String,String] =
    case "finicky" => "FINICKY"

  val finickyOption = finicky.lift

  test("Partial functions throw MatchErrors for non matches") {
    assert(finicky("finicky") == "FINICKY")

    try
      finicky("other")
      assert(false, "Should not be here!")
    catch
      case e: scala.MatchError => assert(e.getMessage.contains("other"))
  }

  test("A partial function can be lifted into a function return Option") {
    assert(finickyOption("finicky") == Some("FINICKY"))
    assert(finickyOption("other") == None)
  }

  test("A function return Option can be 'lowered' to a partial function") {
    val finicky2 = Function.unlift(finickyOption)

    assert(finicky2("finicky") == "FINICKY")

    try
      finicky2("other")
      assert(false, "Should not be here!")
    catch
      case e: scala.MatchError => assert(e.getMessage.contains("other"))
  }
