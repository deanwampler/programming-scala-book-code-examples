// src/main/scala/TypeSystem/structuraltypes/observer-func.scala

package TypeSystem.structuraltypes

trait SubjectFunc {                                                  // <1>

  type State

  type Observer = State => Unit                                      // <2>

  private var observers: List[Observer] = Nil

  def addObserver(observer:Observer): Unit =
    observers ::= observer

  def notifyObservers(state: State): Unit =                          // <5>
    observers foreach (o => o(state))
}
