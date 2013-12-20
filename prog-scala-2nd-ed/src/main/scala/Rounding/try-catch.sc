// code-examples/Rounding/try-catch-script.scala

import java.util.Calendar

val then = null
val now = Calendar.getInstance()

try {
  now.compareTo(then)
} catch {
  case e: NullPointerException => println("One was null!"); System.exit(-1)
  case unknown => println("Unknown exception " + unknown); System.exit(-1)
} finally {
  println("It all worked out.")
  System.exit(0)
}
