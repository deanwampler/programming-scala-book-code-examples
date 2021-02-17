// src/script/scala/progscala3/traits/ui/ButtonCountObserver1.scala
import progscala3.traits.ui.*
import progscala3.traits.observer.*

val button = new ObservableButton("Click Me!"):
  def updateUI(): Unit = println(s"$label clicked")

val bco1 = ButtonCountObserver()
val bco2 = ButtonCountObserver()

button.addObserver(bco1)
button.addObserver(bco2)

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 5)
assert(bco2.count == 5)
