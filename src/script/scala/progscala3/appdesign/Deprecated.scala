// src/script/scala/progscala3/appdesign/Deprecated.scala

import scala.annotation.nowarn

@deprecated("this method will be removed", "V1.2.3")       // <1>
def obsolete(i: Int) = 2*i

def warning(i: Int) = obsolete(i)                          // <2>
@nowarn def nowarning(i: Int) = obsolete(i)                // <3>


def inc(i: Int,
	@deprecatedName("y", "V1.2.3") n: Int): Int = i + n
inc(1, y = 2)
inc(1, n = 2)
