// src/script/scala/progscala3/contexts/ArrowAssocExtension.scala
import scala.annotation.{alpha, infix}

extension [A,B] (a: A):
  @infix @alpha("arrow2") def ~>(b: B): (A, B) = (a, b)
