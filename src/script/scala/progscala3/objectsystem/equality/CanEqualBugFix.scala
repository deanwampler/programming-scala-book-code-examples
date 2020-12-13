// src/script/scala/progscala3/objectsystem/equality/CanEqualBugFix.scala

import scala.language.strictEquality                                 // <1>

case class X(name: String) derives CanEqual                          // <2>

def findMarkers[T](marker: T, seq: Seq[T])(                          // <3>
    using CanEqual[T,T]): Seq[T] = seq.filter(_ == marker)

findMarkers(X("marker"), Seq(X("one"), X("two"), X("marker"), X("three")))

// Refactoring
case class Y(name: String) derives CanEqual
findMarkers(Y("marker"), Seq(Y("one"), Y("two"), Y("marker"), Y("three")))

