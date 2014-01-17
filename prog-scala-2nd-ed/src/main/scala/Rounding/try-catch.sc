// src/main/scala/Rounding/try-catch.sc

import java.util.Calendar

val start = null
val now = Calendar.getInstance()

try {
  now.compareTo(start)
} catch {
  case e: NullPointerException => println("One was null!"); System.exit(-1)
  case other: Throwable => println(s"Other exception: $other"); System.exit(-1)
} finally {
  println("It all worked out.")
  System.exit(0)
}
