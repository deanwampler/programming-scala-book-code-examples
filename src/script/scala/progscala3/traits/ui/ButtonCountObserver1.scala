// src/script/scala/progscala3/traits/ui/ButtonCountObserver1.scala
import progscala3.traits.ui._
import progscala3.traits.observer._

val button = new ObservableButton("Click Me!"):
  def updateUI(): Unit = println(s"$label clicked")

val bco1   = new ButtonCountObserver
val bco2   = new ButtonCountObserver

button.addObserver(bco1)
button.addObserver(bco2)

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 5)
assert(bco2.count == 5)
