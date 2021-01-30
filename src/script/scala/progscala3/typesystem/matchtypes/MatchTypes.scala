// tag::matchdef1[]
// src/script/scala/progscala3/typesystem/matchtypes/MatchTypes.scala

type Elem[X] = X match                                          // <1>
  case String => Char                                           // <2>
  case IterableOnce[t] => t                                     // <3>
  case Array[t] => t
  case ? => X                                                   // <4>
// end::matchdef1[]

// tag::examples1[]
val char: Elem[String] = 'c'
val doub: Elem[List[Double]] = 1.0
val tupl: Elem[Option[(Int,Double)]] = (1, 2.0)

val bad1: Elem[List[Double]] = "1.0"        // ERROR
val bad2: Elem[List[Double]] = (1.0, 2.0)   // ERROR
// end::examples1[]

// tag::examples2[]
summon[Elem[String] =:= Char]  // ...: Char =:= Char = generalized constraint
summon[Elem[List[Int]] =:= Int]
summon[Elem[Nil.type] =:= Nothing]                              // <1>
summon[Elem[Array[Float]] =:= Float]
summon[Elem[Option[String]] =:= String]
summon[Elem[Some[String]] =:= String]
summon[Elem[None.type] =:= Nothing]
summon[Elem[Float] =:= Float]

summon[Elem[Option[List[Long]]] =:= Long]                       // <2>
summon[Elem[Option[List[Long]]] =:= List[Long]]
// end::examples2[]

// The rest of this script is not included in the book, but another example of
// recursive match types is discussed.

// tag::matchdefrecur[]
// Use recursion to handle, e.g., `Option[List[Array[Int]]`, but notice what
// breaks or changes in the examples!
type ElemR[X] = X match         // "R" for "recursive"
  case String => Char
  case IterableOnce[t] => ElemR[t]
  case Array[t] => ElemR[t]
  case ? => X
// end::matchdefrecur[]

// tag::examplesrecur[]
val char: ElemR[String] = 'c'
val doub: ElemR[List[Double]] = 1.0
val tupl: ElemR[Option[(Int,Double)]] = (1, 2.0)

val bad1: ElemR[List[Double]] = "1.0"        // ERROR
val bad2: ElemR[List[Double]] = (1.0, 2.0)   // ERROR

summon[ElemR[String] =:= Char]
summon[ElemR[List[Int]] =:= Int]
summon[ElemR[Nil.type] =:= Nothing]          // Now fails: Recall Nil =:= List[Nothing]
summon[ElemR[Array[Float]] =:= Float]
summon[ElemR[Option[String]] =:= Char]       // Was String!
summon[ElemR[Some[String]] =:= Char]         // Was String!
summon[ElemR[None.type] =:= Nothing]         // Now fails: Recall None =:= Option[Nothing]
summon[ElemR[Float] =:= Float]
summon[ElemR[Option[List[Long]]] =:= Long]   // Now works!
// end::examplesrecur[]

// Here are more variations, all of which attempt to abstract over all
// parameterized types with a single type parameter (not that the code above is
// so bad...).

// These uses of abstraction over the higher-kinded type work, but are too
// unwieldy to be useful:
type ElemF[X,F[_]] = X match
  case String => Char
  case F[t] => t

summon[ElemF[String,Array] =:= Char]  // ...: Char =:= Char = generalized constraint
summon[ElemF[List[Int],List] =:= Int] // ...: Int =:= Int = generalized constraint
summon[ElemF[List[Int],?] =:= Int]    // Cannot prove that ...

// Using a "type lambda" also works, but no less awkwardly:
type ElemF[X] = [F[_]] =>> X match
  case String => Char
  case F[t] => t

summon[ElemF[String][Array] =:= Char]  // ...: Char =:= Char = generalized constraint
summon[ElemF[List[Int]][List] =:= Int] // ...: Int =:= Int = generalized constraint
summon[ElemF[List[Int]][?] =:= Int]    // Cannot prove that ...

// This attempt to use a type alias simply fails:
type HigherF[A,F[_]] = F[A]
type ElemF2[X] = X match
  case String => Char
  case HigherF[t,?] => t

summon[ElemF2[String] =:= Char]     // Char =:= Char = generalized constraint
summon[ElemF2[List[Int]] =:= Int]          // Cannot prove that ...
summon[ElemF2[HigherF[Int,List]] =:= Int]   // Cannot prove that ...
