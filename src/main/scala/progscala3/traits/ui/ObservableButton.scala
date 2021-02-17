// src/main/scala/progscala3/traits/ui/ObservableButton.scala
package progscala3.traits.ui
import progscala3.traits.observer.*

abstract class ObservableButton(name: String)                        // <1>
    extends Button(name) with Subject[Button]:                       // <2>

  override def click(): Unit =                                       // <3>
    super.click()                                                    // <4>
    notifyObservers(this)                                            // <5>
