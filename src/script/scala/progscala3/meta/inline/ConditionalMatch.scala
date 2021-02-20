// src/script/scala/progscala3/meta/inline/ConditionalMatch.scala

inline def repeat2(s: String, count: Int): String =
  inline if count == 0 then ""
  else s + repeat2(s, count-1)

repeat2("hello", 3)    // Okay
var n = 3
repeat2("hello", n)    // ERROR!

inline def repeat3(s: String, count: Int): String =
  inline count match
    case 0 => ""
    case _ => s + repeat3(s, count-1)

repeat3("hello", 3)    // Okay
var n2 = 3
repeat3("hello", n2)   // ERROR!
