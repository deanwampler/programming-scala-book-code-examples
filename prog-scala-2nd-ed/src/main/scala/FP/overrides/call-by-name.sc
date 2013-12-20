// code-examples/FP/overrides/call-by-name-script.scala

def whileAwesome(conditional: => Boolean)(f: => Unit) {
  if (conditional) {
    f
    whileAwesome(conditional)(f)
  }
}

var count = 0

whileAwesome(count < 5) {
  println("still awesome")
  count += 1
}