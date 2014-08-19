// src/main/scala/progscala2/dynamic/clinq-example.sc
import progscala2.dynamic.CLINQ

def makeMap(name: String, capital: String, statehood: Int) =
  Map("name" -> name, "capital" -> capital, "statehood" -> statehood)

// "Records" for Five of the states in the U.S.A.
val states = CLINQ(
  List(
    makeMap("Alaska",     "Juneau",      1959),
    makeMap("California", "Sacramento",  1850),
    makeMap("Illinois",   "Springfield", 1818),
    makeMap("Virginia",   "Richmond",    1788),
    makeMap("Washington", "Olympia",     1889)))

// Field projections ("SELECT ..."):
states.name
states.capital
states.statehood
states.name_and_capital
states.name_and_statehood
states.capital_and_statehood
states.name_capital_and_statehood
states.all

// Add "WHERE" clauses.
states.all.where("name").NE("Alaska")
states.all.where("statehood").EQ(1889)
states.name_and_statehood.where("statehood").NE(1850)
