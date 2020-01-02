// src/main/scala/progscala3/fp/curry/curried-func.sc

def cat1(s1: String)(s2: String) = s1 + s2
def cat2(s1: String) = (s2: String) => s1 + s2

assert(cat1("hello")("world") == cat2("hello")("world"))

def cat3(s1: String, s2: String) = s1 + s2

val cat3Curried = (cat3 _).curried
// cat3Curried: String => (String => String) = <function1>

assert(cat3Curried("hello")("world") == "helloworld")

assert(cat3("hello", "world") == "helloworld")

val cat3Uncurried = Function.uncurried(cat3Curried)
// cat3Uncurried: (String, String) => String = <function2>

cat3Uncurried("hello", "world")
assert(cat3Uncurried("hello", "world") == "helloworld")
