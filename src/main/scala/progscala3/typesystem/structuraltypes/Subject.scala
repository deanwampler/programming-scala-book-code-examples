// src/main/scala/progscala3/typesystem/structuraltypes/Subject.scala
package progscala3.typesystem.structuraltypes

trait Subject:                                                       // <1>

  import scala.language.reflectiveCalls                              // <2>
  import reflect.Selectable.reflectiveSelectable

  type State                                                         // <3>

  type Observer = {                                                  // <4>
    def receiveUpdate(state: Any): String
  }

  private var observers: Vector[Observer] = Vector.empty             // <5>

  def addObserver(observer:Observer): Unit =
    observers :+= observer

  def notifyObservers(state: State): Seq[String] =
    observers map (_.receiveUpdate(state))
