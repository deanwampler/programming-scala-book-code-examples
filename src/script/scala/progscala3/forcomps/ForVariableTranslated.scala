// src/script/scala/progscala3/forcomps/ForVariableTranslated.scala

val map = Map("one" -> 1, "two" -> 2)

val seq = for
  (key, value) <- map   // How are this line and the next one translated?
  i10 = value + 10
yield (i10)

val seq2 = for
  (i, i10) <- for
    x1 @ (key, value) <- map
  yield {
    val x2 @ i10 = value + 10
    (x1, x2)
  }
yield (i10)
