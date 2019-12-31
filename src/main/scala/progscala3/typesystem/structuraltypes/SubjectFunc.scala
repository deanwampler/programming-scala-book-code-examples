// src/main/scala/progscala3/typesystem/structuraltypes/SubjectFunc.scala
package progscala3.typesystem.structuraltypes

trait SubjectFunc {                                                  // <1>

  type State

  type Observer = State => String                                    // <2>

  private var observers: Vector[Observer] = Vector.empty

  def addObserver(observer:Observer): Unit =
    observers :+= observer

  def notifyObservers(state: State): Seq[String] =                   // <3>
    observers map (o => o(state))
}
