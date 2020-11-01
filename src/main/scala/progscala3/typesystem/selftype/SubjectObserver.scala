// src/main/scala/progscala3/typesystem/selftype/SubjectObserver.scala
package progscala3.typesystem.selftype

abstract class SubjectObserver:
  type S <: Subject                                                  // <1>
  type O <: Observer

  trait Subject:
    self: S =>                                                       // <2>
    private var observers = List[O]()

    def addObserver(observer: O) = observers ::= observer

    def notifyObservers() = observers.foreach(_.receiveUpdate(self)) // <3>

  trait Observer:
    def receiveUpdate(subject: S): Unit
