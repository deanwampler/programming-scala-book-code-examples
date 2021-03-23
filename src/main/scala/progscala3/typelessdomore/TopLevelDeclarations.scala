// src/main/scala/progscala3/typelessdomore/TopLevelDeclarations.scala
package progscala3.typelessdomore.api

val DefaulCount = 5
def countTo(limit: Int = DefaulCount) = (0 to limit).foreach(println)

class Class1:
  def m = "cm1"

object Object1:
  def m = "om1"
