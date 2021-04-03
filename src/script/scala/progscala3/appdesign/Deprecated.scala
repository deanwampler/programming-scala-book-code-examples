// src/script/scala/progscala3/appdesign/Deprecated.scala
// Not discussed in the book.

import scala.annotation.nowarn    // This one has to be imported.

@deprecated("this method will be removed", "V1.2.3")
def obsolete(i: Int) = 2*i

def warning(i: Int) = obsolete(i)
// In Scala 2, @nowarn would suppress a warning for this method's use of obsolete.
// This is not (yet?) implemented in Scala 3.
@nowarn def nowarning(i: Int) = obsolete(i)

def inc(i: Int,
  @deprecatedName("y", "V1.2.3") n: Int): Int = i + n
// inc(1, y = 2)           // Scala 3 doesn't let you use the name "y"
inc(1, n = 2)
