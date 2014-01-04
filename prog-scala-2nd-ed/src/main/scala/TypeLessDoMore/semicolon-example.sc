// src/main/scala/TypeLessDoMore/semicolon-example.sc

// Trailing equals sign indicates more code on the next line
def equalsign = {
  val reallySuperLongValueNameThatGoesOnForeverSoYouNeedANewLine =
    "wow that was a long value name"

  println(reallySuperLongValueNameThatGoesOnForeverSoYouNeedANewLine)
}

// Trailing opening curly brace indicates more code on the next line
def equalsign2(s: String) = {
  println("equalsign2: " + s)
}

// Trailing comma, operators, etc. indicate more code on the next line
def commas(s1: String,
           s2: String) = {
  println("comma: " + s1 + 
          ", " + s2)
}
