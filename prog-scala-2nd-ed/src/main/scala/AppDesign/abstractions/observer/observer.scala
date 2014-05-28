// src/main/scala/AppDesign/abstractions/observer/observer.scala

package appdesign.abstractions.observer

abstract class SubjectObserver {
  type S <: Subject
  type O <: Observer
  
  trait Subject {
    self: S =>    // #1
    private var observers = List[O]()
    def addObserver(observer: O) = observers ::= observer
    def notifyObservers() = observers foreach (_.receiveUpdate(self))  // #2
  }
  
  trait Observer {
    self: O =>
    def receiveUpdate(subject: S)
  }
}
