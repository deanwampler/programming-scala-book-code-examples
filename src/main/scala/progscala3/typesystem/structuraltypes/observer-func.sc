// src/main/scala/progscala3/typesystem/structuraltypes/observer-func.sc
import progscala3.typesystem.structuraltypes.SubjectFunc

def makeObserverFunc(id: Int): Int => String = 
  (state: Int) => s"$id: got one! $state"

val subject = new SubjectFunc {
  type State = Int
  protected var count = 0

  def increment(): Seq[String] = {
    count += 1
    notifyObservers(count)
  }
}

assert(subject.increment() == Nil)
assert(subject.increment() == Nil)
subject.addObserver(makeObserverFunc(1))
assert(subject.increment() == List("1: got one! 3"))
subject.addObserver(makeObserverFunc(2))
assert(subject.increment() == List("1: got one! 4", "2: got one! 4"))
assert(subject.increment() == List("1: got one! 5", "2: got one! 5"))
