// src/main/scala/progscala2/metaprogramming/quasiquotes.sc
import reflect.runtime.universe._

// Bring in the "toolbox":
import reflect.runtime.currentMirror
import tools.reflect.ToolBox
val toolbox = currentMirror.mkToolBox()

val C = q"case class C(s: String)"
showCode(C)
showRaw(C)

// q != tq
val  q =  q"List[String]"
val tq = tq"List[String]"
showRaw(q)
showRaw(tq)
q equalsStructure tq

// "Unquoting", analogous to textual substitution in source code.
Seq(tq"Int", tq"String") map { param =>
  q"case class C(s: $param)"
} foreach { q =>
  println(showCode(q))
}

// Lifting
val list = Seq(1,2,3,4)
val fmt = "%d, %d, %d, %d"
val printq = q"println($fmt, ..$list)"

// Unlifting (pattern matching, too)
val q"${i: Int} + ${d: Double}" = q"1 + 3.14"
