// src/main/scala/progscala2/typesystem/structuraltypes/Observer.scala
package progscala2.typesystem.structuraltypes

trait Subject {                                                      // <1>

  import scala.language.reflectiveCalls                              // <2>

  type State                                                         // <3>

  type Observer = { def receiveUpdate(state: Any): Unit }            // <4>

  private var observers: List[Observer] = Nil                        // <5>

  def addObserver(observer:Observer): Unit =
    observers ::= observer

  def notifyObservers(state: State): Unit =
    observers foreach (_.receiveUpdate(state))
}
