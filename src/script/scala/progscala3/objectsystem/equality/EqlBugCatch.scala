// src/script/scala/progscala3/objectsystem/equality/EqlBugFix.scala

import scala.language.strictEquality                                 // <1>

case class X(name: String) derives Eql                               // <2>

def findMarkers[T](marker: T, seq: Seq[T])(using Eql[T,T]): Seq[T] = // <3>
  seq.filter(_ == marker)

findMarkers(X("marker"), Seq(X("one"), X("two"), X("marker"), X("three")))

// Refactoring
case class Y(name: String) derives Eql
findMarkers(Y("marker"), Seq(Y("one"), Y("two"), Y("marker"), Y("three")))

