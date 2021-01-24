// src/main/scala/progscala3/collections/ListBuilder.scala
package progscala3.collections

import collection.mutable.Builder

class ListBuilder[T] extends Builder[T, List[T]]:

  private var storage = Vector.empty[T]

  def addOne(elem: T): ListBuilder.this.type =
    storage = storage :+ elem
    this

  // inherited from trait Growable

  def clear(): Unit = storage = Vector.empty[T]

  def result(): List[T] = storage.toList

@main def TryListBuilder =
  val lb = ListBuilder[Int]
  (1 to 3) foreach (i => lb += i)
  assert(lb.result == List(1, 2, 3))
