// src/main/scala/progscala3/typesystem/structuraltypes/SubjectFunc.scala
package progscala3.typesystem.structuraltypes

trait SubjectFunc:
  type State                                                         // <1>

  private var observers: Vector[State => Unit] = Vector.empty        // <2>
  def addObserver(observer: State => Unit): Unit =
    observers :+= observer
  def notifyObservers(state: State): Seq[Unit] =                     // <3>
    observers map (o => o(state))
