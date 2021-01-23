// src/main/scala/progscala3/traits/observer/Observer.scala
package progscala3.traits.observer

trait Observer[State]:                                               // <1>
  def receiveUpdate(state: State): Unit

trait Subject[State]:                                                // <2>
  private var observers: Vector[Observer[State]] = Vector.empty      // <3>

  def addObserver(observer: Observer[State]): Unit =                 // <4>
    observers.synchronized { observers :+= observer }                // <5>

  def notifyObservers(state: State): Unit =                          // <6>
    observers foreach (_.receiveUpdate(state))
