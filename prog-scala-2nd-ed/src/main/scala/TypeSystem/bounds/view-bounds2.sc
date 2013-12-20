// code-examples/TypeSystem/bounds/view-bounds2-script.scala

import bounds._

// Variation showing a view function value.

implicit val any2Node = (a: Any) => bounds.::[Any](a, NilNode)

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

// Rewriting the view bounds to use an implicit converter instead.

case class LinkedList2[A](val head: Node[A])(implicit a2Node: (A) => Node[A]) {
  
  def ::[B >: A](x: Node[B])(implicit b2Node: (B) => Node[B]) = 
    LinkedList2(bounds.::(x.payload, head))
    
  override def toString = head.toString
}

val list1b = LinkedList2(1)
val list2b = 2 :: list1b
val list3b = 3 :: list2b
val list4b = "FOUR!" :: list3b

println(list1b)
println(list2b)
println(list3b)
println(list4b)
