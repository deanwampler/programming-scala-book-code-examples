// src/script/scala/progscala3/typesystem/structuraltypes/ObserverFunc.scala
import progscala3.typesystem.structuraltypes.SubjectFunc             // <1>

case class Counter(start: Int = 0) extends SubjectFunc:              // <2>
  type State = Int
  var count = start
  def increment(): Unit =
    count += 1
    notifyObservers(count)

var observed1: Int = 0                                               // <3>
var observed2: Int = 0

val observer1: Int => Unit = i => observed1 += 1  // ignore i
val observer2: Int => Unit = i => observed2 += 1  // ignore i

val c = Counter()
c.increment()
c.addObserver(observer1)
c.increment()
c.addObserver(observer2)
c.increment()
assert(c.count == 3)
assert(observed1 == 2)
assert(observed2 == 1)
