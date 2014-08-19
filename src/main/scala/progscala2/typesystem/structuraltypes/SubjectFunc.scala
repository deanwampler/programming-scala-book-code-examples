// src/main/scala/progscala2/typesystem/structuraltypes/SubjectFunc.scala
package progscala2.typesystem.structuraltypes

trait SubjectFunc {                                                  // <1>

  type State

  type Observer = State => Unit                                      // <2>

  private var observers: List[Observer] = Nil

  def addObserver(observer:Observer): Unit =
    observers ::= observer

  def notifyObservers(state: State): Unit =                          // <3>
    observers foreach (o => o(state))
}
