// src/script/scala/progscala3/meta/compiletime/SummonFrom.scala

import scala.compiletime.summonFrom

trait A; trait B

inline def trySummonFrom(): Int = summonFrom {                  // <1>
  case given A => 1
  case given B => 2
  case _ => 0
 }

def tryNone =                                                   // <2>
  println(s"trySummonFrom() == 0? ${trySummonFrom()}")

def tryB =                                                      // <3>
  given B
  println(s"trySummonFrom() == 2? ${trySummonFrom()}")

tryNone
tryB
