// src/script/scala/progscala3/meta/compiletime/SummonAll.scala
import scala.compiletime.summonAll

trait C; trait D; trait E
// In Scala 3.0.0, the following has to be written: given c: C with {}
given c: C()
given d: D()

summonAll[C *: D *: EmptyTuple]
summonAll[C *: D *: E *: EmptyTuple]                            // <1>
