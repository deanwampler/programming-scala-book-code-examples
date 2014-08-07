// src/main/scala/progscala2/collections/list-builder.sc

import collection.mutable.Builder

class ListBldr[T] extends Builder[T, List[T]] {
  
  private var storage = Vector.empty[T]
  
  def +=(elem: T) = {
    storage = storage :+ elem
    this
  }
  
  def clear(): Unit = { storage = Vector.empty[T] }

  def result(): List[T] = storage.toList
}

val lb = new ListBldr[Int]
(1 to 3) foreach (i => lb += i)
lb.result
// Result: List(1, 2, 3)
