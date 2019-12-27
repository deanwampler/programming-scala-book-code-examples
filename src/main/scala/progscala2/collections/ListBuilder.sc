// src/main/scala/progscala2/collections/ListBuilder.sc
import collection.mutable.Builder

class ListBuilder[T] extends Builder[T, List[T]] {

  private var storage = Vector.empty[T]

  def addOne(elem: T): ListBuilder.this.type = {
    storage = storage :+ elem
    this
  }

  // inherited from trait Growable

  def clear(): Unit = { storage = Vector.empty[T] }

  def result(): List[T] = storage.toList
}

val lb = new ListBuilder[Int]
(1 to 3) foreach (i => lb += i)
println(lb.result)
// Result: List(1, 2, 3)
