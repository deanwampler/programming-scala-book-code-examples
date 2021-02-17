// src/script/scala/progscala3/typesystem/selftype/ButtonSubjectObserver.scala
import progscala3.typesystem.selftype.*
import ButtonSubjectObserver.*

val buttons = Vector(
  ObservableButton("one"),
  ObservableButton("two"))
val observer = ButtonClickObserver()

buttons foreach (_.addObserver(observer))
for (i <- 0 to 2) buttons(0).click()
for (i <- 0 to 4) buttons(1).click()

assert(observer.clicks == Map("one" -> 3, "two" -> 5))
