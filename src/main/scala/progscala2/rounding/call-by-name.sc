// src/main/scala/progscala2/rounding/call-by-name.sc

@annotation.tailrec                                                  // <1>
def continue(conditional: => Boolean)(body: => Unit) {               // <2>
  if (conditional) {                                                 // <3>
    body                                                             // <4>
    continue(conditional)(body)
  }
}

var count = 0                                                        // <5>
continue(count < 5) {
  println(s"at $count")
  count += 1
}
