// tag::dtlist[]
// src/script/scala/progscala3/typesystem/deptypes/DependentTypes.scala

import scala.compiletime.ops.int.*

sealed trait DTList[N <: Int]:                                       // <1>
  inline def size: N = valueOf[N]                                    // <2>

  def +:[H <: Matchable](h: H): DTNonEmptyList[N, H, this.type] =    // <3>
    DTNonEmptyList(h, this)

case object DTNil extends DTList[0]                                  // <4>

case class DTNonEmptyList[N <: Int, H <: Matchable, T <: DTList[N]]( // <5>
    head: H, tail: T) extends DTList[S[N]]
// end::dtlist[]

// tag::usage[]
val list = 1 +: 2.2 +: DTNil
list.size
list.head
list.tail
list.tail.size
list.tail.head
list.tail.tail
list.tail.tail.size

list.tail.tail.head      // ERROR
list.tail.tail.tail      // ERROR

DTNil.size
DTNil.head               // ERROR - "head is not a member of ..."
DTNil.tail               // ERROR - "tail is not a member of ..."
// end::usage[]

// tag::simplelist[]
// This example is mentioned in the book but not shown. It is implemented without
// dependent typing, essentially like the library's `List` type. This is easier
// to understand than DTList above, but as shown in the comments below (and
// discussed in the book), you pay penalties due to weaker typing!
sealed trait SList[+T]:
  def size: Int
  def head: T
  def tail: SList[T]
  def +:[T2 >: T](head: T2): SList[T2] = SNonEmptyList(head, this)

case object SNil extends SList[Nothing]:
  def size: Int = 0
  def head: Nothing = throw EmptyList("head")
  def tail: SList[Nothing] = throw EmptyList("head")

case class SNonEmptyList[T, T2 >: T](head: T2, tail: SList[T]) extends SList[T2]:
  def size: Int = 1 + tail.size

case class EmptyList(method: String) extends RuntimeException(s"$method called on SNil")

val slist = 1 +: 2.2 +: SNil
slist.size
slist.head                // Returns AnyVal instead of Int
slist.tail
slist.tail.size
slist.tail.head           // Returns AnyVal instead of Double
slist.tail.tail
slist.tail.tail.size

slist.tail.tail.head      // Compiles, but throws a runtime exception!
slist.tail.tail.tail      // Compiles, but throws a runtime exception!
// end::simplelist[]
