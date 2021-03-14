// src/main/scala/progscala3/typesystem/structuraltypes/Subject.scala
package progscala3.typesystem.structuraltypes
import reflect.Selectable.reflectiveSelectable                       // <1>

private type Observer = {                                            // <2>
  def update(): Unit
}

trait Subject:                                                       // <3>
  protected var observers: Vector[Observer] = Vector.empty
  def addObserver(observer: Observer): Unit =
    observers :+= observer
  def notifyObservers(): Unit =                                      // <4>
    observers foreach (_.update())
