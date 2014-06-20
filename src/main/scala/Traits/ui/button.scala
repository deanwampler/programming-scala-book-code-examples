// src/main/scala/Traits/ui/button.scala

package traits.ui

class Button(val label: String) extends Widget {
  
  def click(): Unit = updateUI()       

  def updateUI(): Unit = { /* logic to change GUI appearance */ }
}
