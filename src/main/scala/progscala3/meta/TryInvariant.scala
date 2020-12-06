// src/main/scala/progscala3/meta/TryInvariant.scala
package progscala3.meta

@main def TryInvariant =
  var i = 0
  invariant(i >= 0, s"i = $i")(i += 1)
  println(s"success: $i")
  println(s"Will now fail:")
  invariant(i >= 0, s"i = $i")(i -= 2)
