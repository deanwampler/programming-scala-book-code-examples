// src/main/scala/progscala2/fp/curry/curried-func.sc

def cat1(s1: String)(s2: String) = s1 + s2
def cat2(s1: String) = (s2: String) => s1 + s2
def cat3(s1: String, s2: String) = s1 + s2

val cat3Curried = (cat3 _).curried
// cat3Curried: String => (String => String) = <function1>

cat3Curried("hello")("world")
// helloworld

cat3("hello", "world")
// helloworld

val cat3Uncurried = Function.uncurried(cat3Curried)
// cat3Uncurried: (String, String) => String = <function2>

cat3Uncurried("hello", "world")
// helloworld