// src/script/scala/progscala3/basicoop/MatchableOpaque.scala

object Obj:
  type Arr[T] = Array[T]
  opaque type OArr[T] = Array[T]

summon[Obj.Arr[Int] <:< Matchable]     // Okay
summon[Obj.OArr[Int] <:< Matchable]    // Doesn't work

object Obj2:
  type Arr[T] = Array[T]
  opaque type OArr[T] <: Matchable = Array[T]

summon[Obj2.Arr[Int] <:< Matchable]     // Okay
summon[Obj2.OArr[Int] <:< Matchable]    // Now okay!

