// src/test/scala/progscala3/fp/datastructs/SeqSuite.scala
package progscala3.fp.datastructs

import munit.*

class SeqSuite extends FunSuite:

  val seq = Seq("Programming", "Scala")

  test("Construct a seq with the Seq() constructor") {
    assert(seq.size == 2)
  }

  test("A constructed seq with the Seq() constructor is a List") {
    assert(seq.isInstanceOf[List[String]])
  }

  test("Construct a seq with the x +: y +: seq concatenation") {
    val seq1 = "People" +: "should" +: "read" +: seq
    assert(seq1 ==
      Seq("People", "should", "read", "Programming", "Scala"))
  }

  test("Construct a seq with the x +: y +: Nil construction") {
    val seq1 = "Programming" +: "Scala" +: Nil
    assert(seq.size == 2)
  }

  test("Join seqs with ++") {
    val seq1 = "People" +: "should" +: "read" +: Nil
    val seq2 = seq1 ++ seq
    assert(seq2 ==
      Seq("People", "should", "read", "Programming", "Scala"))
  }
