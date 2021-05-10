// src/script/scala/progscala3/fp/basics/PartialApplication.scala
// The book uses a slightly shorter version of the following:

def cat1(s1: String)(s2: String) = s1 + s2
def cat2(s1: String) = (s2: String) => s1 + s2

cat1("hello")("world")      // Call with both parameter lists.

val fcat1a = cat1           // No arguments applied (DOES NOT WORK WITH -source 3.1!)
fcat1a("hello")             // One argument list applied
fcat1a("hello")("world")    // Both argument lists applied

val fcat1b = cat1("hello")  // One argument list applied
fcat1b("world")             // Second argument list applied

cat2("hello")("world")      // Same usage as cat1!

val fcat2a = cat2           // No arguments applied
fcat2a("hello")             // One argument list applied
fcat2a("hello")("world")    // Both argument lists applied
val fcat2b = cat2("hello")  // One argument list applied
fcat2b("world")             // Second argument list applied

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
