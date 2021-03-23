// src/script/scala/progscala3/typelessdomore/MethodNestedReturn.scala
// ERROR: Won't compile until you put a String return type on upCase.

def upCase(s: String) =
  if s.length == 0 then
    return s    // COMPILATION ERROR - forces declaration of upCase return type
  else
    s.toUpperCase
