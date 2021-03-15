// src/script/scala/progscala3/dynamic/CLINQ.scala
import progscala3.dynamic.CLINQ

def makeMap(
  name: String, capital: String, year: Int): Map[String,Any] =
  Map("name" -> name, "capital" -> capital, "year" -> year)

// Sample "records"
val data = List(
  makeMap("Alaska",     "Juneau",      1959),
  makeMap("California", "Sacramento",  1850),
  makeMap("Illinois",   "Springfield", 1818))

val states = CLINQ(data)

states.name
states.capital
states.year

states.name_and_capital
states.name_and_year
states.capital_and_year

states.name_and_capital_and_year
states.all

states.all.where("year").EQ(1818)
states.all.where("name").NE("Alaska")

states.name_and_capital.where("capital").EQ("Sacramento")
states.name_and_capital.where("name").NE("Alaska")
states.name_and_year.where("capital").EQ("Springfield")
