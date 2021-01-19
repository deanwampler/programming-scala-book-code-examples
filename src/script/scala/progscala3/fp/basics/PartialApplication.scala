// src/script/scala/progscala3/fp/basics/PartialApplication.scala

def cat1(s1: String)(s2: String) = s1 + s2
def cat2(s1: String) = (s2: String) => s1 + s2

cat1("hello")("world")
val cat1b = cat1("hello")
cat1b("world")

cat2("hello")("world")
val cat2b = cat2("hello")
cat2b("world")

val cat2F = (s1: String) => (s2: String) => s1+s2
val cat2Fb: String =>  String => String  = s1 => s2 => s1+s2
val cat2Fc: String => (String => String) = s1 => s2 => s1+s2

cat2F("hello")("world")
val c2f = cat2F("hello")
c2f("world")

cat2Fb("hello")("world")
val c2fb = cat2Fb("hello")
c2fb("world")

cat2Fc("hello")("world")
val c2fc = cat2Fc("hello")
c2fc("world")
