// src/script/scala/progscala3/typelessdomore/MethodBroadInference.scala
// ERROR: function compiles, but the last line doesn't.

def upperCase(strings: Seq[String]) =
  if strings.length == 0 then
    Seq(0)  // #1
  else
    strings.map(_.toUpperCase)

val seq: Seq[String] = upperCase(Nil)  // COMPILATION ERROR
