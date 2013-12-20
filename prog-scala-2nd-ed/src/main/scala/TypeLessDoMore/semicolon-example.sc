// code-examples/TypeLessDoMore/semicolon-example-script.scala

// Trailing equals sign indicates more code on next line
def equalsign = {
  val reallySuperLongValueNameThatGoesOnForeverSoYouNeedANewLine =
    "wow that was a long value name"

  println(reallySuperLongValueNameThatGoesOnForeverSoYouNeedANewLine)
}

// Trailing opening curly brace indicates more code on next line
def equalsign2(s: String) = {
  println("equalsign2: " + s)
}

// Trailing comma, operator, etc. indicates more code on next line
def commas(s1: String,
           s2: String) = {
  println("comma: " + s1 + 
          ", " + s2)
}
