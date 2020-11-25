// src/script/scala/progscala3/objectsystem/equality/CanEqualBug.scala

case class X(name: String)                                           // <1>

def findMarkers[T](seq: Seq[T]): Seq[T] =                            // <2>
  seq.filter(_ == X("marker"))

findMarkers(Seq(X("one"), X("two"), X("marker"), X("three")))        // <3>

// Refactoring
case class Y(name: String)                                           // <4>
findMarkers(Seq(Y("one"), Y("two"), Y("marker"), Y("three")))        // <5>
