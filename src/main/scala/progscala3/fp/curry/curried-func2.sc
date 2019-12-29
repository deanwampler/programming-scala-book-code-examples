// src/main/scala/progscala3/fp/curry/curried-func2.sc

val f1: String =>  String => String  = (s1: String) => (s2: String) => s1 + s2
val f2: String => (String => String) = (s1: String) => (s2: String) => s1 + s2

assert(f1("hello")("world") == "helloworld")

assert(f2("hello")("world") == "helloworld")

val ff1 = Function.uncurried(f1)
val ff2 = Function.uncurried(f2)

assert(ff1("hello", "world") == "helloworld")

assert(ff2("hello", "world") == "helloworld")
