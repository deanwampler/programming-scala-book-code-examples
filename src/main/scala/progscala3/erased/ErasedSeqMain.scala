package progscala3.erased

import scala.language.experimental.erasedDefinitions

@main def Main() =
  // Use the +: methods to construct sequences.
  val nes1 = "one" +: EmptySeq
  val nes2 = "two" +: nes1
  val nes3 = "three" +: nes2

  // println(s"EmptySeq.head = ${EmptySeq.head}")     // COMPILATION ERROR
  // println(s"EmptySeq.tail = ${EmptySeq.tail}")     // COMPILATION ERROR
  
  println(s"nes1 = $nes1")
  println(s"nes1.head = ${nes1.head}")
  println(s"nes1.tail = ${nes1.tail}")
  // println(s"nes1.tail.head = ${nes1.tail.head}")   // COMPILATION ERROR
  // println(s"nes1.tail.tail = ${nes1.tail.tail}")   // COMPILATION ERROR

  println(s"nes2 = $nes2")
  println(s"nes2.head = ${nes2.head}")
  println(s"nes2.tail = ${nes2.tail}")
  println(s"nes2.tail.head = ${nes2.tail.head}")
  println(s"nes2.tail.tail = ${nes2.tail.tail}")
  // println(s"nes2.tail.tail.head = ${nes2.tail.tail.head}")   // COMPILATION ERROR
  // println(s"nes2.tail.tail.tail = ${nes2.tail.tail.tail}")   // COMPILATION ERROR
  
  println(s"nes3 = $nes3")
  println(s"nes3.head = ${nes3.head}")
  println(s"nes3.tail = ${nes3.tail}")
  println(s"nes3.tail.head = ${nes3.tail.head}")
  println(s"nes3.tail.tail = ${nes3.tail.tail}")
  println(s"nes3.tail.tail.head = ${nes3.tail.tail.head}")
  println(s"nes3.tail.tail.tail = ${nes3.tail.tail.tail}")
  // println(s"nes3.tail.tail.tail.head = ${nes3.tail.tail.tail.head}")  // COMPILATION ERROR
  // println(s"nes3.tail.tail.tail.tail = ${nes3.tail.tail.tail.tail}")  // COMPILATION ERROR

  // Use the NotEmptySeq.apply methods
  val nes1b = NotEmptySeq("one")
  val nes2b = NotEmptySeq("two", nes1b)
  val nes3b = NotEmptySeq("three", nes2b)

  println(s"nes1b = $nes1b")
  println(s"nes1b.head = ${nes1b.head}")
  println(s"nes1b.tail = ${nes1b.tail}")
  // println(s"nes1b.tail.head = ${nes1b.tail.head}")   // COMPILATION ERROR
  // println(s"nes1b.tail.tail = ${nes1b.tail.tail}")   // COMPILATION ERROR

  println(s"nes2b = $nes2b")
  println(s"nes2b.head = ${nes2b.head}")
  println(s"nes2b.tail = ${nes2b.tail}")
  println(s"nes2b.tail.head = ${nes2b.tail.head}")
  println(s"nes2b.tail.tail = ${nes2b.tail.tail}")
  // println(s"nes2b.tail.tail.head = ${nes2b.tail.tail.head}")   // COMPILATION ERROR
  // println(s"nes2b.tail.tail.tail = ${nes2b.tail.tail.tail}")   // COMPILATION ERROR
  
  println(s"nes3b = $nes3b")
  println(s"nes3b.head = ${nes3b.head}")
  println(s"nes3b.tail = ${nes3b.tail}")
  println(s"nes3b.tail.head = ${nes3b.tail.head}")
  println(s"nes3b.tail.tail = ${nes3b.tail.tail}")
  println(s"nes3b.tail.tail.head = ${nes3b.tail.tail.head}")
  println(s"nes3b.tail.tail.tail = ${nes3b.tail.tail.tail}")
  // println(s"nes3b.tail.tail.tail.head = ${nes3b.tail.tail.tail.head}")  // COMPILATION ERROR
  // println(s"nes3b.tail.tail.tail.tail = ${nes3b.tail.tail.tail.tail}")  // COMPILATION ERROR
