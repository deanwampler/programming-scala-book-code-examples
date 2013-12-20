// code-examples/TypeSystem/bounds/view-bounds-script.scala

import bounds._

implicit def any2Node[A](x: A): Node[A] = bounds.::[A](x, NilNode)

case class LinkedList[A <% Node[A]](val head: Node[A]) {

  def ::[B >: A <% Node[B]](x: Node[B]) = 
    LinkedList(bounds.::(x.payload, head))
    
  override def toString = head.toString
}

val list1 = LinkedList(1)
val list2 = 2 :: list1
val list3 = 3 :: list2
val list4 = "FOUR!" :: list3

println(list1)
println(list2)
println(list3)
println(list4)
