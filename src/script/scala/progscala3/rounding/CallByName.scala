// src/script/scala/progscala3/rounding/CallByName.scala
import scala.annotation.tailrec

@tailrec                                                             // <1>
def continue(conditional: => Boolean)(body: => Unit): Unit =         // <2>
  if conditional then                                                // <3>
    body
    continue(conditional)(body)

var count = 0
continue (count < 5) {                                               // <4>
  println(s"at $count")
  count += 1
}
assert(count == 5)

count = 0
continue (count < 5)
  println(s"at $count")
  count += 1
