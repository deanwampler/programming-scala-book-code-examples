// src/script/scala/progscala3/fp/basics/PartialApplication.scala

def cat1(s1: String)(s2: String) = s1 + s2
def cat2(s1: String) = (s2: String) => s1 + s2
def cat1(s1: String)(s2: String): String
def cat2(s1: String): String => String

cat1("hello")("world")
cat2("hello")("world")

val cat2F = (s1: String) => (s2: String) => s1+s2
val cat2Fb: String => (String => String) = s1 => s2 => s1+s2

val c2f = cat2F("hello")
c2f("world")

def cat3(s1: String, s2: String) = s1 + s2  // One parameter list.
cat3("hello")

def f123 =
  (one:String) => (two:String) => (three:String) => s"$one|$two|$three"
val f23 = f123("first")
val f3 = f23("second")
val s123 = f3("third")


