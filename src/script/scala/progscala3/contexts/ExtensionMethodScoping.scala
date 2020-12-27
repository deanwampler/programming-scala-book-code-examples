// src/script/scala/progscala3/contexts/ExtensionMethodScoping.scala

val s = "Hello World!"

trait T:
  extension (s: String) def LOUD: String = s.toUpperCase

s.LOUD  // error; LOUD not in scope.

object S2 extends T:
  def loud(s: String): String = s.LOUD

S2.loud(s)

object S2:
  extension (s: String) def soft: String = s.toLowerCase

s.soft

import S2.soft
s.soft
