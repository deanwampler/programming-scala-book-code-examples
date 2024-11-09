// src/script/scala/progscala3/meta/compiletime/SummonFrom.scala
import scala.compiletime.summonFrom

trait A; trait B

inline def trySummonFrom(label: String, expected: Int): Unit =  // <1>
  val actual = summonFrom {
    case given A => 1
    case given B => 2
    case _ => 0
  }
  printf("%-9s trySummonFrom(): %d =?= %d\n", label, expected, actual)

def tryNone = trySummonFrom("tryNone:", 0)                      // <2>

def tryA =                                                      // <3>
  // In Scala 3.0.0, the following has to be written: given A: {}
  given A()
  trySummonFrom("tryA:", 1)

def tryB =
  given B()
  trySummonFrom("tryB:", 2)

def tryAB =
  given A()
  given B()
  trySummonFrom("tryAB:", 1)

tryNone; tryA; tryB; tryAB
