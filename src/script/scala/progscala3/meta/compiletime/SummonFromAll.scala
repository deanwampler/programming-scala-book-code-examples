// src/script/scala/progscala3/meta/compiletime/SummonFromAll.scala

import scala.compiletime.summonFrom

trait A; trait B; trait C

inline def trySummonFrom(): Int = summonFrom {                  // <1>
  case given A => 1
  case given B => 2
  case _ => 0
 }

def tryNone =                                                   // <2>
  println(s"trySummonFrom() == 0? ${trySummonFrom()}")

def tryB =                                                      // <3>
  given B with {}
  println(s"trySummonFrom() == 2? ${trySummonFrom()}")

tryNone
tryB

import scala.compiletime.summonAll

def allAB =
  given a: A with {}
  given b: B with {}
  summonAll[A *: B *: EmptyTuple]
  summonAll[A *: B *: C *: EmptyTuple]                          // <1>
