// src/main/scala/progscala3/typesystem/selftype/ButtonSubjectObserver.scala
package progscala3.typesystem.selftype

case class Button(label: String):                                    // <1>
  def click(): Unit = {}

object ButtonSubjectObserver extends SubjectObserver:                // <2>
  type S = ObservableButton
  type O = Observer

  class ObservableButton(label: String) extends Button(label) with Subject:
    override def click() =                                           // <3>
      super.click()
      notifyObservers()

  class ButtonClickObserver extends Observer:                        // <4>
   val clicks = scala.collection.mutable.HashMap[String,Int]()

    def receiveUpdate(button: ObservableButton): Unit =
      val count = clicks.getOrElse(button.label, 0) + 1
      clicks.update(button.label, count)

@main def TryButtonSubjectObserver() =
  import ButtonSubjectObserver.*

  val button1 = ObservableButton("one")
  val button2 = ObservableButton("two")
  val observer = ButtonClickObserver()
  button1.addObserver(observer)
  button2.addObserver(observer)
  button1.click()
  button2.click()
  button1.click()
  println(observer.clicks)
