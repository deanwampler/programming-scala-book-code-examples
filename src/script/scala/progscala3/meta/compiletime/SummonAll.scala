// src/script/scala/progscala3/meta/compiletime/SummonAll.scala

import scala.compiletime.summonAll

trait A; trait B; trait C

given a as A
given b as B
summonAll[A *: B *: EmptyTuple]
summonAll[A *: B *: C *: EmptyTuple]
