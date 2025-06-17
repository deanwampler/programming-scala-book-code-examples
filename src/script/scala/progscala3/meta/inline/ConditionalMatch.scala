// src/script/scala/progscala3/meta/inline/ConditionalMatch.scala

inline def repeat2(s: String, count: Int): String =
  inline if count == 0 then ""
  else s + repeat2(s, count-1)

repeat2("hello", 3)    // Okay
inline val n1 = 3
repeat2("hello", n1)   // Okay, because m is declared inline
val n2 = 3
repeat2("hello", n2)   // ERROR! 

inline def repeat3(s: String, count: Int): String =
  inline count match
    case 0 => ""
    case _ => s + repeat3(s, count-1)

repeat3("hello", 3)    // Okay
inline var n3 = 3      // ERROR!
//repeat3("hello", n3)
var n4 = 3
repeat3("hello", n4)   // ERROR!
