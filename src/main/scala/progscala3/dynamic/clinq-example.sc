// src/main/scala/progscala3/dynamic/clinq-example.sc
import progscala3.dynamic.CLINQ

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

// Field projections ("SELECT ..."):
println(s"states.name: ${states.name}")
println(s"states.capital: ${states.capital}")
println(s"states.statehood: ${states.statehood}")
println(s"states.name_and_capital: ${states.name_and_capital}")
println(s"states.name_and_statehood: ${states.name_and_statehood}")
println(s"states.capital_and_statehood: ${states.capital_and_statehood}")
val ncs = states.name_and_capital_and_statehood // To shorten the next line...
println(s"states.name_and_capital_and_statehood: ${ncs}")
println(s"states.all: ${states.all}")

// Add "WHERE" clauses.
val alaska = states.all.where("name").NE("Alaska")
println(s"""states.all.where("name").NE("Alaska"): ${alaska}""")
val year1889 = states.all.where("statehood").EQ(1889)
println(s"""states.all.where("statehood").EQ(1889): ${year1889}""")
val not1850 = states.name_and_statehood.where("statehood").NE(1850)
val not1850Str = """states.name_and_statehood.where("statehood").NE(1850)"""
println(s"""${not1850Str}: ${not1850}""")
val wash = states.name_and_statehood.where("capital").EQ("Olympia")
val washStr = """states.name_and_statehood.where("capital").EQ("Olympia")"""
println("BUT as implemented, WHERE clauses only work with projected fields;")
println(s"""${washStr} returns an empty list CLINQ(Nil):""")
println(s"""[${wash}]""")
