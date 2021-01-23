// src/script/scala-2/progscala3/objectsystem/hashcode/CaseObjectHashCode.scala

case object O1          // case object with no members

case object O2 {        // case object with two members
  val f = "O2"
  def m(i:Int): String = i.toString
}
object O3 {
  case object O4        // nested in another type
}

println(s"O1:           ${O1.hashCode} == ${"O1".hashCode}")
println(s"O2:           ${O2.hashCode} == ${"O2".hashCode}")
println(s"O3:           ${O3.hashCode} != ${"O3".hashCode}")
println(s"O3.O4:        ${O3.O4.hashCode} != ${"O3.O4".hashCode}")
println(s"03.04 vs. 04: ${O3.O4.hashCode} == ${"O4".hashCode}")
println(s"03.04 vs. 03: ${O3.O4.hashCode} == ${"O3".hashCode}")
