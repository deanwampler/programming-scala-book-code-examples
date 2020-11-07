// src/script/scala/progscala3/contexts/ArrowAssocExtension.scala

extension [A,B] (a: A):
  def ~>(b: B): (A, B) = (a, b)
