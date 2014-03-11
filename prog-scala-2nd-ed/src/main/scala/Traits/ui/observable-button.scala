// src/main/scala/Traits/ui/observable-button.scala

package traits.ui

import traits.observer._

class ObservableButton(name: String)                       // <1>      
    extends Button(name) with Subject[Button] {            // <2>

  override def click(): Unit = {                           // <3>
    super.click()                                          // <4>
    notifyObservers(this)                                  // <5>
  }
}
