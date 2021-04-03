// src/script/scala/progscala3/typesystem/structuraltypes/ObserverFunc.scala
import progscala3.typesystem.structuraltypes.SubjectFunc        // <1>

case class Counter(start: Int = 0) extends SubjectFunc:         // <2>
  type State = Int
  var count = start
  def increment(): Unit =
    count += 1
    notifyObservers(count)

case class CounterObserver(var updateCalledCount: Int = 0) {    // <3>
  def apply(count: Int): Unit = updateCalledCount += 1
}
val observer1 = CounterObserver()
val observer2 = CounterObserver()

val c = Counter()
c.increment()
c.addObserver(observer1.apply)                                  // <4>
c.increment()
c.addObserver(observer2.apply)
c.increment()
assert(c.count == 3)
assert(observer1.updateCalledCount == 2)
assert(observer2.updateCalledCount == 1)
