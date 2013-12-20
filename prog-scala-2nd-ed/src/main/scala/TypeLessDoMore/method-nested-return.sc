// code-examples/TypeLessDoMore/method-nested-return-script.scala
// ERROR: Won't compile until you put a String return type on upCase.

def upCase(s: String) = {
  if (s.length == 0)
    return s    // ERROR - forces return type of upCase to be declared.
  else
    s.toUpperCase()
}

println( upCase("") )
println( upCase("Hello") )
