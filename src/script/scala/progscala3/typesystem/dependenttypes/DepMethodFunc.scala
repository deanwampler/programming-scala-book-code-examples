// end::ll[]
// src/script/scala/progscala3/typesystem/dependenttypes/DepMethodFunc.scala
trait LinkedList:
  type Item                                                          // <1>
  def head: Item                                                     // <2>
  def tail: Option[LinkedList]

def head(ll: LinkedList): ll.Item = ll.head                          // <3>
val h: (ll: LinkedList) => ll.Item = _.head
def tail(ll: LinkedList): Option[LinkedList] = ll.tail
val t: (ll: LinkedList) => Option[LinkedList] = _.tail
// end::ll[]

// tag::intll[]
case class IntLinkedList(head: Int, tail: Option[IntLinkedList])     // <1>
    extends LinkedList:
  type Item = Int

val ill = IntLinkedList(0,
  Some(IntLinkedList(1, Some(IntLinkedList(2, None)))))
assert(head(ill) == 0)
assert(tail(ill) == Some(IntLinkedList(1,Some(IntLinkedList(2,None)))))
assert(head(tail(ill).get) == 1)                                     // <2>
assert(head(tail(tail(ill).get).get) == 2)

assert(h(ill) == 0)
assert(t(ill) == Some(IntLinkedList(1,Some(IntLinkedList(2,None)))))
assert(h(t(ill).get) == 1)
assert(h(t(t(ill).get).get) == 2)
// end::intll[]

// tag::stringll[]
case class StringLinkedList(head: String, tail: Option[StringLinkedList])
    extends LinkedList:
  type Item = String

val sll = StringLinkedList("zero", Some(StringLinkedList("one",
  Some(StringLinkedList("two", None)))))
assert(head(sll) == "zero")
assert(tail(sll) ==
  Some(StringLinkedList("one",Some(StringLinkedList("two",None)))))
assert(head(tail(sll).get) == "one")
assert(head(tail(tail(sll).get).get) == "two")

assert(h(sll) == "zero")
assert(t(sll) ==
  Some(StringLinkedList("one",Some(StringLinkedList("two",None)))))
assert(h(t(sll).get) == "one")
assert(h(t(t(sll).get).get) == "two")
// end::stringll[]


val ill2 = IntLinkedList(0,
  Some(StringLinkedList("one", Some(IntLinkedList(2, None)))))
