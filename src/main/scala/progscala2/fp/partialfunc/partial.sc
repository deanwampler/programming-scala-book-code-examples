// src/main/scala/progscala2/fp/partialfunc/partial.sc

def concatUpper(s1: String, s2: String): String = (s1 + " " + s2).toUpperCase

val c = concatUpper _
println(c("short", "pants"))

val c2 = concatUpper("short", _: String)
println(c2("pants"))
