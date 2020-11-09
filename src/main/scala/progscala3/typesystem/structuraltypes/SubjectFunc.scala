// src/main/scala/progscala3/typesystem/structuraltypes/SubjectFunc.scala
package progscala3.typesystem.structuraltypes

trait SubjectFunc:                                                   // <1>
  type State                                                         // <2>

  private var observers: Vector[State => Unit] = Vector.empty        // <3>
  def addObserver(observer: State => Unit): Unit =
    observers :+= observer
  def notifyObservers(state: State): Seq[Unit] =                     // <4>
    observers map (o => o(state))
