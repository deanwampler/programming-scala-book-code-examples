// src/script/scala/progscala3/appdesign/Deprecated.scala
// Not discussed in the book.

import scala.annotation.nowarn    // This one has to be imported.

@deprecated("this method will be removed", "V1.2.3")
def obsolete(i: Int) = 2*i

def warning(i: Int) = obsolete(i)
@nowarn def nowarning(i: Int) = obsolete(i)  // No warning for this method

def inc(i: Int,
  @deprecatedName("y", "V1.2.3") n: Int): Int = i + n
// inc(1, y = 2)           // Scala 3 doesn't let you use the name "y"
inc(1, n = 2)
