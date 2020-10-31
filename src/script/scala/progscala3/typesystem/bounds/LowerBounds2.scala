// src/script/scala/progscala3/typesystem/bounds/LowerBounds2.scala

// Consider an invariant option. This seems to work, but you run into problems
// if you try, e.g., `val Opt[Parent] = Ope(new Child(1))`
case class Opt[A](value: A = null):
  def getOrElse(default: A): A = if (value != null) value else default

val a: Parent = Opt[Parent](null).getOrElse(new Parent(10))
val b: Child  = Opt(new Child(1)).getOrElse(new Child(10))
val c: Parent = Opt[Parent](null).getOrElse(new Parent(10))
val d: Parent = Opt[Parent](null).getOrElse(new Child(10))
val e: Parent  = Opt(new Parent(1)).getOrElse(new Child(10))
val f: Parent  = Opt(new Child(1)).getOrElse(new Parent(10))
