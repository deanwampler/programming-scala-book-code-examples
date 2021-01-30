// src/script/scala/progscala3/typesystem/structuraltypes/Observer.scala
import progscala3.typesystem.structuraltypes.Subject
import scala.reflect.Selectable.reflectiveSelectable

case class Counter(start: Int = 0) extends Subject:             // <1>
  var count = start
  def increment(): Unit =
    count += 1
    notifyObservers()

case class CounterObserver(counter: Counter):                   // <2>
  var updateCount = 0
  def update(): Unit = updateCount += 1

val c = Counter()
c.increment()
val observer1 = CounterObserver(c)
c.addObserver(observer1)
c.increment()
val observer2 = CounterObserver(c)
c.addObserver(observer2)
c.increment()
assert(c.count == 3)
assert(observer1.updateCount == 2)
assert(observer2.updateCount == 1)                              // <3>
