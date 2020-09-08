// src/main/scala/progscala3/typesystem/abstracttypes/SubjectObserver.scala
package progscala3.typesystem.abstracttypes

abstract class SubjectObserver:                                      // <1>
  type S <: Subject                                                  // <2>
  type O <: Observer

  trait Subject:                                                     // <3>
    private var observers = List[O]()

    def addObserver(observer: O): Unit = observers ::= observer

    def notifyObservers(): Unit =                                    // <4>
      observers.foreach(_.receiveUpdate(this))

  trait Observer:                                                    // <5>
    def receiveUpdate(subject: Subject): Unit
