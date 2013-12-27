// src/main/scala/Rounding/match-variable.sc

import scala.util.Random

val randomInt = new Random().nextInt(10)

randomInt match {
  case 7 => println("lucky seven!")
  case otherNumber => println("boo, got boring ol' " + otherNumber)
}
