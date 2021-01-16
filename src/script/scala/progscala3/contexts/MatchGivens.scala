// tag::definitions[]
// src/script/scala/progscala3/contexts/MatchGivens.scala

trait Witness                                                        // <1>
case object IntWitness extends Witness
case object StringWitness extends Witness

def useWitness(using Witness): String = summon[Witness].toString     // <2>
// end::definitions[]

// tag::usage[]
useWitness                                                           // <1>

for given Witness <- Seq(IntWitness, StringWitness)                  // <2>
do println(useWitness)

useWitness                                                           // <3>

Seq(IntWitness -> "Int", StringWitness -> "String") foreach {        // <4>
  case (witness @ given Witness, y) => println(s"witness: $useWitness -> $y")
}

useWitness                                                           // <5>
// end::usage[]
