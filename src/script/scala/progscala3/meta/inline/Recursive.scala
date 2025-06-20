// src/script/scala/progscala3/meta/inline/Recursive.scala

inline def repeat(s: String, count: Int): String =
  if count == 0 then ""
  else s + repeat(s, count-1)

repeat("hello", 3)    // Okay
inline val m = 3
repeat("hello", m)    // Okay, because m is declared inline
val n = 3
repeat("hello", n)    // ERROR! 
