// tag::dtlist[]
// src/script/scala/progscala3/typesystem/deptypes/DependentTypes.scala

import scala.compiletime.S
import scala.compiletime.ops.int._

object DTList:        // "DT" for dependent type
  type NSize[N] <: Int = N match                                // <1>
    case 0 => 0
    case ? => S[NSize[N-1]]

  sealed trait DTList[N <: Int]:                                // <2>
    inline def size: NSize[N] = valueOf[NSize[N]]               // <3>

    def +:[H](h: H): DTNonEmptyList[N, H, this.type] =          // <4>
      DTNonEmptyList(h, this)

  case object DTNil extends DTList[0]                           // <5>
  case class DTNonEmptyList[N <: Int, H, T <: DTList[N]](       // <6>
    head: H, tail: T) extends DTList[S[N]]
// end::dtlist[]

// tag::usage[]
val list = 1 +: 2.2 +: DTList.DTNil
list.size
list.head
list.tail
list.tail.size
list.tail.head
list.tail.tail
list.tail.tail.size

list.tail.tail.head
list.tail.tail.tail
// end::usage[]

// tag::simplelist[]
// This example is mentioned in the book but not shown. It is implemented without
// dependent typing, essentially like the library's `List` type. This is easier
// to understand than DTList above, but as shown in the comments below (and
// discussed in the book), you pay penalties due to weaker typing!
object SList:        // "S" for "simple list"
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

val slist = 1 +: 2.2 +: SList.SNil
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
