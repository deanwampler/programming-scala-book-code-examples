// src/script/scala/progscala3/meta/compiletime/SummonAll.scala
import scala.compiletime.summonAll

trait C; trait D; trait E
given a: C with {}
given b: D with {}

summonAll[C *: D *: EmptyTuple]
summonAll[C *: D *: E *: EmptyTuple]                            // <1>
