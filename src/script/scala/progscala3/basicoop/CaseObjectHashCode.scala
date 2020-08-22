// src/script/scala/progscala3/basicoop/CaseObjectHashCode.scala

case object O1

case object O2:
  val f = "O2"

object O3:
  case object O4

println(s"O1:           ${O1.hashCode} == ${"O1".hashCode}")
println(s"O2:           ${O2.hashCode} == ${"O2".hashCode}")
println(s"O3:           ${O3.hashCode} != ${"O3".hashCode}")
println(s"O3.O4:        ${O3.O4.hashCode} != ${"O3.O4".hashCode}")
println(s"O4:           ${O4.hashCode} != ${"O4".hashCode}")
println(s"03.04 vs. 04: ${O3.O4.hashCode} == ${"O4".hashCode}")
