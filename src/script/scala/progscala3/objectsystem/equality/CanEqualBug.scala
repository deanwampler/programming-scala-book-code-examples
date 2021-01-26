// src/script/scala/progscala3/objectsystem/equality/CanEqualBug.scala

case class X(name: String)

def findMarkers[T](seq: Seq[T]): Seq[T] =                            // <1>
  seq.filter(_ == X("marker"))

findMarkers(Seq(X("one"), X("two"), X("marker"), X("three")))        // <2>

case class Y(name: String)                                           // <3>
findMarkers(Seq(Y("one"), Y("two"), Y("marker"), Y("three")))        // <4>
