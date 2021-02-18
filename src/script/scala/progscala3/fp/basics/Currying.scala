// src/script/scala/progscala3/fp/basics/Currying.scala

def mcat(s1: String, s2: String) = s1 + s2
val mcatCurried = mcat.curried

val fcat = (s1: String, s2: String) => s1 + s2
val fcatCurried = fcat.curried

mcat("hello", "world")
fcat("hello", "world")
mcatCurried("hello")("world")
fcatCurried("hello")("world")

def mcat2(s1: String) = (s2: String) => s1 + s2
val mcat2Uncurried = Function.uncurried(mcat2)
val mcatUncurried = Function.uncurried(mcatCurried)

mcat2("hello")("world")
mcat2Uncurried("hello", "world")
mcatUncurried("hello", "world")
