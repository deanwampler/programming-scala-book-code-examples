// src/script/scala/progscala3/typesystem/structuraltypes/ObserverFunc.scala
import progscala3.typesystem.structuraltypes.SubjectFunc             // <1>

case class Counter(start: Int = 0) extends SubjectFunc:              // <2>
  type State = Int
  var count = start
  def increment(): Unit =
    count += 1
    notifyObservers(count)

var observerCount1 = 0                                               // <3>
var observerCount2 = 0
val observer1: Int => Unit = i => observerCount1 += 1
val observer2: Int => Unit = i => observerCount2 += 1

val c = Counter()
c.increment()
c.addObserver(observer1)
c.increment()
c.addObserver(observer2)
c.increment()
assert(c.count == 3)
assert(observerCount1 == 2)
assert(observerCount2 == 1)
