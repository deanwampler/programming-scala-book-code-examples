// src/main/scala/progscala2/typesystem/structuraltypes/SubjectFunc.sc
import progscala2.typesystem.structuraltypes.SubjectFunc

val observer: Int => Unit = (state: Int) => println("got one! "+state)

val subject = new SubjectFunc {
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
