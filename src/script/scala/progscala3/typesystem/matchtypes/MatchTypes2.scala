// src/script/scala/progscala3/typesystem/matchtypes/MatchTypes2.scala
// Not in the book. More experimenting with match types.
import compiletime.asMatchable

type Elem[X <: Matchable] = X match
  case String => Char
  case Array[t] => t
  case Iterable[t] => t
  case Option[t] => t
  case Matchable => X

val c: Elem[String] = 'c'
val i: Elem[Array[Int]] = 0
val d: Elem[Seq[Double]] = 1.1
val t: Elem[Option[(Double,String)]] = (1.1, "hello")
val a: (Double, String) = (2.2, "world")

// Parses fine, but it assume no empty collections or None, so "unsafe".
def lastUnsafe[X <: Matchable](in: X): Elem[X] = in match
  case s: String => s.last
  case a: Array[t] => a.last
  case i: Iterable[t] => i.last
  case o: Option[t] => o.get
  case m: Matchable => m

lastUnsafe("hello")
lastUnsafe(Array(1,2,3))
lastUnsafe(Seq(1,2,3))
lastUnsafe(Some(2))
lastUnsafe(10)
// But...
lastUnsafe("")
lastUnsafe(Array.empty[Int])
lastUnsafe(Nil)
lastUnsafe(None)

// Does this parse and provide a safe implementation? No; it has trouble
// confirming that Char =:= Elem[String] for "s.last" and
// Matchable =:= Elem[Matchable] for the default Matchable clause.

def lastOrElse[X <: Matchable](in: X)(default: => Elem[X]): Elem[X] = in.asMatchable match
  case s: String => if s.isEmpty then default else s.last
  case a: Array[Elem[X]] => if a.isEmpty then default else a.last
  case i: Iterable[Elem[X]] => if i.isEmpty then default else i.last
  case o: Option[Elem[X]] => o.getOrElse(default)
  case m: Matchable => m

// What about using options? Same parsing problems...
def lastOption[X <: Matchable](in: X): Option[Elem[X]] = in.asMatchable match
  case s: String => s.lastOption
  case a: Array[Elem[X]] => a.lastOption
  case i: Iterable[Elem[X]] => i.lastOption
  case o: Option[Elem[X]] => o
  case m: Matchable => Some(m)

// Surely this will work, no? No; it seems that any attempt to combine the
// match type Elem[X] with other types, whether "| Null", Option[...], or
// using it as the type of a default argument fails to work.
def lastOrNull[X <: Matchable](in: X): Elem[X] | Null = in.asMatchable match
  case s: String => if s.isEmpty then null else s.last
  case a: Array[Elem[X]] => if a.isEmpty then null else a.last
  case i: Iterable[Elem[X]] => if i.isEmpty then null else i.last
  case o: Option[Elem[X]] => o.getOrElse(null)
  case m: Matchable => m

// Look at a recursive match type:

type RElem[X <: Matchable] = X match
  case String => Char
  case Array[t] => RElem[t]
  case Iterable[t] => RElem[t]
  case Option[t] => RElem[t]
  case Matchable => X

def rlastUnsafe[X <: Matchable](in: X): RElem[X] = in.asMatchable match
  case s: String => s.last
  case a: Array[t] => rlastUnsafe(a.last)
  case i: Iterable[t] => rlastUnsafe(i.last)
  case o: Option[t] => rlastUnsafe(o.get)
  case m: Matchable => m

// def rlastOrElse[X](in: X, default: RElem[X]): RElem[X] = in match
//   case s: String => if s.isEmpty then default else s.last
//   case a: Array[RElem[X]] => rlastOrElse(if a.isEmpty then default else a.last)
//   case i: Iterable[RElem[X]] => rlastOrElse(if i.isEmpty then default else i.last)
//   case o: Option[RElem[X]] => rlastOrElse(o.getOrElse(default))
//   case a: Any => a

def rlastOrElse[X](in: X, default: RElem[X]): RElem[X] = in.asMatchable match
  case s: String => s.lastOption.getOrElse(default)
  case a: Array[RElem[X]] => rlastOrElse(a.lastOption.getOrElse(default))
  case i: Iterable[RElem[X]] => rlastOrElse(i.lastOption.getOrElse(default))
  case o: Option[RElem[X]] => rlastOrElse(o.getOrElse(default))
  case a: Any => a

def rlastOption[X](in: X): Option[RElem[X]] = in.asMatchable match
  case s: String => s.lastOption
  case a: Array[RElem[X]] => a.lastOption.flatMap(x => rlastOption(x))
  case i: Iterable[RElem[X]] => i.lastOption.flatMap(x => rlastOption(x))
  case o: Option[RElem[X]] => o.flatMap(x => rlastOption(x))
  case a: Any => Some(a)

def rlastOrNull[X](in: X): RElem[X] | Null = in.asMatchable match
  case s: String => if s.isEmpty then null else s.last
  case a: Array[RElem[X]] => if a.isEmpty then null else rlastOrNull(a.last)
  case i: Iterable[RElem[X]] => if i.isEmpty then null else rlastOrNull(i.last)
  case o: Option[RElem[X]] => if o.isEmpty then null else rlastOrNull(o.last)
  case a: Any => a

val str  = "01234"
val arr  = Array(2.2, 3.3, 4.4, 5.5)
val seq1 = Seq(0.0F, 1.1F, 2.2F, 3.3F)
val seq2 = Seq(Some(0L), None, Some(3L))
val opt1 = Some("pi" -> Math.Pi)
val opt2 = Some(Seq(Array("e" -> Math.E)))
val any  = 1.1

val strL  = rlastUnsafe(str)
val arrL  = rlastUnsafe(arr)
val seq1L = rlastUnsafe(seq1)
val seq2L = rlastUnsafe(seq2)
val opt1L = rlastUnsafe(opt1)
val opt2L = rlastUnsafe(opt2)
val anyL  = rlastUnsafe(any)
