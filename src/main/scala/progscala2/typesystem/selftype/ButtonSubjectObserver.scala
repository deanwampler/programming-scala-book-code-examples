// src/main/scala/progscala2/typesystem/selftype/ButtonSubjectObserver.scala
package progscala2.typesystem.selftype

case class Button(label: String) {                                   // <1>
  def click(): Unit = {}
}

object ButtonSubjectObserver extends SubjectObserver {               // <2>
  type S = ObservableButton
  type O = ButtonObserver

  class ObservableButton(label: String) extends Button(label) with Subject {
    override def click() = {
      super.click()
      notifyObservers()
    }
  }

  trait ButtonObserver extends Observer {
    def receiveUpdate(button: ObservableButton)
  }
}

import ButtonSubjectObserver._

class ButtonClickObserver extends ButtonObserver {                   // <3>
 val clicks = new scala.collection.mutable.HashMap[String,Int]()

  def receiveUpdate(button: ObservableButton) = {
    val count = clicks.getOrElse(button.label, 0) + 1
    clicks.update(button.label, count)
  }
}
