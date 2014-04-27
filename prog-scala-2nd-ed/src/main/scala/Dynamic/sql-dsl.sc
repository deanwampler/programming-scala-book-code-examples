// src/main/scala/Dynamic/sql-dsl.sc

import dynamic.CLINQ

// "Records" for Five of the states in the U.S.A.
val states = CLINQ(List(
  Map("name" -> "Alaska",     "capital" -> "Juneau",      "statehood" -> 1959),
  Map("name" -> "California", "capital" -> "Sacramento",  "statehood" -> 1850),
  Map("name" -> "Illinois",   "capital" -> "Springfield", "statehood" -> 1818),
  Map("name" -> "Virginia",   "capital" -> "Richmond",    "statehood" -> 1788),
  Map("name" -> "Washington", "capital" -> "Olympia",     "statehood" -> 1889)
))

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
