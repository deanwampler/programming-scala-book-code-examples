// src/script/scala/progscala3/typesystem/structuraltypes/Observer.scala
import progscala3.typesystem.structuraltypes.Subject
import scala.language.reflectiveCalls

case class Observer(id: Int):                                        // <1>
  def receiveUpdate(state: Any): String = s"$id: got one! $state"

val subject = new Subject {                                          // <2>
  type State = Int
  protected var count = 0
  def increment(): Seq[String] =
    count += 1
    notifyObservers(count)
}
assert(subject.increment() == Nil)
assert(subject.increment() == Nil)
subject.addObserver(new Observer(1))
assert(subject.increment() == List("1: got one! 3"))
subject.addObserver(new Observer(2))
assert(subject.increment() == List("1: got one! 4", "2: got one! 4"))
assert(subject.increment() == List("1: got one! 5", "2: got one! 5"))
