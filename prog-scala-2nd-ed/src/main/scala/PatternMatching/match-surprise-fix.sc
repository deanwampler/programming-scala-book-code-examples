// src/main/scala/PatternMatching/match-surprise-fix.sc

def checkY(y: Int) = {
  for {
    x <- List (99, 100, 101)
  } {
    val str = x match {
      case `y` => "found y!"
      case i: Int => "int: "+i
    }
    println(str)
  }
}

checkY(100)
