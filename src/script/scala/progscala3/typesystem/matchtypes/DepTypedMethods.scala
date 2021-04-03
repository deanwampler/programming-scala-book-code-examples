// tag::first[]
// src/script/scala/progscala3/typesystem/matchtypes/DepTypedMethods.scala

type ElemR[X] = X match         // "R" for "recursive"
  case String => Char
  case Array[t] => ElemR[t]                                          // <1>
  case Iterable[t] => ElemR[t]                                       // <2>
  case Option[t] => ElemR[t]
  case AnyVal => X

import compiletime.asMatchable                                       // <3>
def first[X](x: X): ElemR[X] = x.asMatchable match                   // <4>
  case s: String      => s.charAt(0)
  case a: Array[t]    => first(a(0))
  case i: Iterable[t] => first(i.head)
  case o: Option[t]   => first(o.get)
  case x: AnyVal      => x
// end::first[]

// tag::example[]
case class C(name: String)
object O
first("one")
first(Array(2.2, 3.3))
first(Seq("4", "five"))
first(6)
first(true)
first(O)
first(C("Dean"))
// end::example[]
