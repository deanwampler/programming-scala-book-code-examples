// src/main/scala/Traits/ui/button-count-observer2.sc

import traits.ui._
import traits.observer._

val button = new Button("Click Me!") with Subject[Button] {

  override def click(): Unit = {                            
    super.click()
    notifyObservers(this)
  }
}

class ButtonCountObserver extends Observer[Button] {
  var count = 0
  def receiveUpdate(state: Button): Unit = count += 1
}

val bco1   = new ButtonCountObserver
val bco2   = new ButtonCountObserver

button addObserver bco1
button addObserver bco2

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 5)
assert(bco2.count == 5)
println("Success!")
