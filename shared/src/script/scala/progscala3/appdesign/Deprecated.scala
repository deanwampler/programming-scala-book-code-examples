// src/script/scala/progscala3/appdesign/Deprecated.scala

import scala.annotation.nowarn                             // <1>

@deprecated("this method will be removed", "V1.2.3")       // <2>
def obsolete(i: Int) = 2*i

def warning(i: Int) = obsolete(i)                          // <3>
@nowarn def nowarning(i: Int) = obsolete(i)

def inc(i: Int,
	@deprecatedName("y", "V1.2.3") n: Int): Int = i + n
inc(1, y = 2)                                              // <4>
inc(1, n = 2)
