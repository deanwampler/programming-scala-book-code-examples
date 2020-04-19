// src/script/scala-2/progscala3/rounding/DoWhileAlternative.scala

var count = 0

// The old do/while construct was removed in Scala 3
// do {
//   count += 1
//   println(count)
// } while (count < 10)

// Using the Scala-2 like syntax, you have to write it as follows:
while ({
  count += 1
  println(count)
  count < 10
}) ()

assert(count == 10)