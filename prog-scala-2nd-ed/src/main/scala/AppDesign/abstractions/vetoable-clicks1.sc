// code-examples/AppDesign/abstractions/vetoable-clicks1-script.scala

import appdesign.abstractions._

trait Clickable {
  def click()
}

class Widget
class Button(val label: String) extends Widget with Clickable {
  def click() = println("click!")
}

trait VetoableClicks extends Clickable {
  val maxAllowed = 1
  private var count = 0
  abstract override def click() = {
    if (count < maxAllowed) {
      count += 1
      super.click()
    }
  }
}

val button1 = new Button("click me!")
println("new Button(...)")
for (i <- 1 to 3 ) button1.click()

val button2 = new Button("click me!") with VetoableClicks
println("new Button(...) with VetoableClicks")
for (i <- 1 to 3 ) button2.click()
