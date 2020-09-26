// src/script/scala/progscala3/traits/ui/ButtonCountObserver2.scala
import progscala3.traits.ui._
import progscala3.traits.observer._

// tag::button
val button = new Button("Click Me!") with Subject[Button]:
  override def click(): Unit =
    super.click()
    notifyObservers(this)
  def updateUI(): Unit = println(s"$label clicked")
// end::button

val bco1 = new ButtonCountObserver

button.addObserver(bco1)

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 5)
