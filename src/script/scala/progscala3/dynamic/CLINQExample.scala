// src/script/scala/progscala3/dynamic/CLINQExample.scala
import progscala3.dynamic.CLINQ

def makeMap(
  name: String, capital: String, statehood: Int): Map[String,Any] =
  Map("name" -> name, "capital" -> capital, "statehood" -> statehood)

// Sample "records"
val data = List(
  makeMap("Alaska",     "Juneau",      1959),
  makeMap("California", "Sacramento",  1850),
  makeMap("Illinois",   "Springfield", 1818))

val states = CLINQ(data)

states.name
states.capital
states.statehood

states.name_and_capital
states.name_and_statehood
states.capital_and_statehood

states.name_and_capital_and_statehood
states.all

states.all.where("statehood").EQ(1818)
states.all.where("name").NE("Alaska")

states.name_and_capital.where("capital").EQ("Sacramento")
states.name_and_capital.where("name").NE("Alaska")
states.name_and_statehood.where("capital").EQ("Springfield")
