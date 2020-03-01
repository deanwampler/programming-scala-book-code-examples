// src/main/scala/progscala3/typelessdomore/MethodNestedReturn.sc
// ERROR: Won't compile until you put a String return type on upCase.

def upCase(s: String) = {
  if (s.length == 0)
    return s    // COMPILATION ERROR - forces declaration of upCase return type
  else
    s.toUpperCase()
}
