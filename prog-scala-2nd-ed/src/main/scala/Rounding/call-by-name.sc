// src/main/scala/Rounding/call-by-name.sc

@annotation.tailrec
def continue(conditional: => Boolean)(f: => Unit) {
  if (conditional) {
    f
    continue(conditional)(f)
  }
}

var count = 0

continue(count < 5) {
  println(s"at $count")
  count += 1
}
