// src/main/scala/AdvOOP/observer/observer.scala

package advoop.observer
import scala.language.reflectiveCalls

trait AbstractSubject {
  type Observer

  private var observers = List[Observer]()
  def addObserver(observer:Observer) = observers ::= observer
  def notifyObservers = observers foreach (notify(_))

  def notify(observer: Observer): Unit
}

trait SubjectForReceiveUpdateObservers extends AbstractSubject {
  type Observer = { def receiveUpdate(subject: Any) }

  def notify(observer: Observer): Unit = observer.receiveUpdate(this)
}

trait SubjectForFunctionalObservers extends AbstractSubject {
  type Observer = (AbstractSubject) => Unit

  def notify(observer: Observer): Unit = observer(this)
}
