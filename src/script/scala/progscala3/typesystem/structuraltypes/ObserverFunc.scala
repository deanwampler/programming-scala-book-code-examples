// src/script/scala/progscala3/typesystem/structuraltypes/ObserverFunc.scala
import progscala3.typesystem.structuraltypes.SubjectFunc

def makeObserverFunc(id: Int): Int => String =
  (state: Int) => s"$id: got one! $state"

case object MySubject extends SubjectFunc:
  type State = Int
  protected var count = 0

  def increment(): Seq[String] =
    count += 1
    notifyObservers(count)

assert(MySubject.increment() == Nil)
assert(MySubject.increment() == Nil)
MySubject.addObserver(makeObserverFunc(1))
assert(MySubject.increment() == List("1: got one! 3"))
MySubject.addObserver(makeObserverFunc(2))
assert(MySubject.increment() == List("1: got one! 4", "2: got one! 4"))
assert(MySubject.increment() == List("1: got one! 5", "2: got one! 5"))
