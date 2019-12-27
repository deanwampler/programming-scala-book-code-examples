// src/main/scala/progscala3/typesystem/structuraltypes/Observer.sc
import progscala3.typesystem.structuraltypes.Subject
import scala.language.reflectiveCalls

object observer {                                                    // <1>
  def receiveUpdate(state: Any): Unit = println("got one! "+state)
}

val subject = new Subject {                                          // <2>
  type State = Int
  protected var count = 0

  def increment(): Unit = {
    count += 1
    notifyObservers(count)
  }
}

subject.increment()
subject.increment()
subject.addObserver(observer)
subject.increment()
subject.increment()
