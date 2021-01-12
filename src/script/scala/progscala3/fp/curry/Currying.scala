// src/script/scala/progscala3/fp/curry/Currying.scala

def cat1(s1: String)(s2: String) = s1 + s2
def cat2(s1: String) = (s2: String) => s1 + s2
def cat3(s1: String, s2: String) = s1 + s2

cat1("hello")("world")
cat2("hello")("world")
cat3("hello", "world")

val cat3Curried = cat3.curried
cat3Curried("hello")("world")

// Curried function arguments bind right to left.
// These two definitions are equivalent:
val cat4: String =>  String => String
  = (s1: String) => (s2: String) => s1 + s2
val cat5: String => (String => String)
  = (s1: String) => (s2: String) => s1 + s2

cat4("hello")("world")
cat5("hello")("world")

val cat3b = Function.uncurried(cat3Curried)

cat3b("hello", "world")
