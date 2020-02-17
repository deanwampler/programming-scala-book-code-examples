// src/test/scala/progscala3/dynamic/CLINQSuite.scala
package progscala3.dynamic

import munit._
import progscala3.metaprogramming.requirement

class CLINQSuite extends FunSuite {

  def makeMap(name: String, capital: String, statehood: Int): Map[String,Any] =
    Map("name" -> name, "capital" -> capital, "statehood" -> statehood)

  // "Records" for Five of the states in the U.S.A.
  val states = CLINQ(
    List(
      makeMap("Alaska",     "Juneau",      1959),
      makeMap("California", "Sacramento",  1850),
      makeMap("Illinois",   "Springfield", 1818),
      makeMap("Virginia",   "Richmond",    1788),
      makeMap("Washington", "Olympia",     1889)))

  test("""Field projections ("SELECT ...") projects a single field""") {
    requirement(states.name == CLINQ(Seq(
      Map("name" -> "Alaska"),
      Map("name" -> "California"),
      Map("name" -> "Illinois"),
      Map("name" -> "Virginia"),
      Map("name" -> "Washington"))))
    requirement(states.capital == CLINQ(Seq(
      Map("capital" -> "Juneau"),
      Map("capital" -> "Sacramento"),
      Map("capital" -> "Springfield"),
      Map("capital" -> "Richmond"),
      Map("capital" -> "Olympia"))))
    requirement(states.statehood == CLINQ(Seq(
      Map("statehood" -> 1959),
      Map("statehood" -> 1850),
      Map("statehood" -> 1818),
      Map("statehood" -> 1788),
      Map("statehood" -> 1889))))
  }

  test("""Field projections ("SELECT ...") projects two fields""") {
    requirement(states.name_and_capital == CLINQ(Seq(
      Map("name" -> "Alaska", "capital" -> "Juneau"),
      Map("name" -> "California", "capital" -> "Sacramento"),
      Map("name" -> "Illinois", "capital" -> "Springfield"),
      Map("name" -> "Virginia", "capital" -> "Richmond"),
      Map("name" -> "Washington", "capital" -> "Olympia"))))
    requirement(states.name_and_statehood == CLINQ(Seq(
      Map("name" -> "Alaska", "statehood" -> 1959),
      Map("name" -> "California", "statehood" -> 1850),
      Map("name" -> "Illinois", "statehood" -> 1818),
      Map("name" -> "Virginia", "statehood" -> 1788),
      Map("name" -> "Washington", "statehood" -> 1889))))
    requirement(states.capital_and_statehood == CLINQ(Seq(
      Map("capital" -> "Juneau", "statehood" -> 1959),
      Map("capital" -> "Sacramento", "statehood" -> 1850),
      Map("capital" -> "Springfield", "statehood" -> 1818),
      Map("capital" -> "Richmond", "statehood" -> 1788),
      Map("capital" -> "Olympia", "statehood" -> 1889))))
  }

  test("""Field projections ("SELECT ...") projects three fields""") {
    requirement(states.name_and_capital_and_statehood == CLINQ(Seq(
      Map("name" -> "Alaska", "capital" -> "Juneau", "statehood" -> 1959),
      Map("name" -> "California", "capital" -> "Sacramento", "statehood" -> 1850),
      Map("name" -> "Illinois", "capital" -> "Springfield", "statehood" -> 1818),
      Map("name" -> "Virginia", "capital" -> "Richmond", "statehood" -> 1788),
      Map("name" -> "Washington", "capital" -> "Olympia", "statehood" -> 1889))))
  }

  test("""Field projections ("SELECT ...") projects all fields""") {
    requirement(states.all == CLINQ(Seq(
      Map("name" -> "Alaska", "capital" -> "Juneau", "statehood" -> 1959),
      Map("name" -> "California", "capital" -> "Sacramento", "statehood" -> 1850),
      Map("name" -> "Illinois", "capital" -> "Springfield", "statehood" -> 1818),
      Map("name" -> "Virginia", "capital" -> "Richmond", "statehood" -> 1788),
      Map("name" -> "Washington", "capital" -> "Olympia", "statehood" -> 1889))))
  }

  test("Simple WHERE clauses select individual records") {
    requirement(states.all.where("statehood").EQ(1889) == CLINQ(Seq(
      Map("name" -> "Washington", "capital" -> "Olympia", "statehood" -> 1889))))
  }

  test("Simple WHERE clauses reject individual records") {
    requirement(states.all.where("name").NE("Alaska") == CLINQ(Seq(
      Map("name" -> "California", "capital" -> "Sacramento", "statehood" -> 1850),
      Map("name" -> "Illinois", "capital" -> "Springfield", "statehood" -> 1818),
      Map("name" -> "Virginia", "capital" -> "Richmond", "statehood" -> 1788),
      Map("name" -> "Washington", "capital" -> "Olympia", "statehood" -> 1889))))
  }

  test("Simple WHERE clauses combine with projections") {
    requirement(states.name_and_capital.where("capital").EQ("Olympia") == CLINQ(Seq(
      Map("name" -> "Washington", "capital" -> "Olympia"))))
    requirement(states.name_and_capital.where("name").NE("Alaska") == CLINQ(Seq(
      Map("name" -> "California", "capital" -> "Sacramento"),
      Map("name" -> "Illinois", "capital" -> "Springfield"),
      Map("name" -> "Virginia", "capital" -> "Richmond"),
      Map("name" -> "Washington", "capital" -> "Olympia"))))
  }
  test("Simple WHERE clauses only work with projected fields! [KNOWN LIMITATION]") {
    requirement(states.name_and_statehood.where("capital").EQ("Olympia") == CLINQ(Nil))
  }
}
