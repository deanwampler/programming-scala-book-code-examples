// src/script/scala/progscala3/typesystem/matchtypes/MatchTypes2.scala
// Not in the book. More experimenting with match types.
type Elem[X] = X match
  case String => Char
  case Array[t] => t
  case Iterable[t] => t
  case Option[t] => t
  case Any => X

val c: Elem[String] = 'c'
val i: Elem[Array[Int]] = 0
val d: Elem[Seq[Double]] = 1.1
val t: Elem[Option[(Double,String)]] = (1.1, "hello")
val a: (Double, String) = (2.2, "world")

def lastOrElse[X](in: X, default: Elem[X]): Elem[X] = in match
  case s: String => if s.isEmpty then default else s.last
  case a: Array[t] => if a.isEmpty then default else a.last
  case i: Iterable[t] => if i.isEmpty then default else i.last
  case o: Option[t] => o.getOrElse(default)
  case a: Any => a

def lastOption[X](in: X): Option[Elem[X]] = in match
  case s: String => s.lastOption
  case a: Array[t] => a.lastOption
  case i: Iterable[t] => i.lastOption
  case o: Option[t] => o
  case a: Any => Some(a)

def lastUnSafe[X](in: X): Elem[X] = in match
  case s: String => s.last
  case a: Array[t] => a.last
  case i: Iterable[t] => i.last
  case o: Option[t] => o.get
  case a: Any => a


type RElem[X] = X match
  case String => Char
  case Array[t] => RElem[t]
  case Iterable[t] => RElem[t]
  case Option[t] => RElem[t]
  case Any => X

def rlastUnsafe[X](in: X): RElem[X] = in match
  case s: String => s.last
  case a: Array[t] => rlastUnsafe(a.last)
  case i: Iterable[t] => rlastUnsafe(i.last)
  case o: Option[t] => rlastUnsafe(o.get)
  case a: Any => a

// def rlastOrElse[X](in: X, default: RElem[X]): RElem[X] = in match
//   case s: String => if s.isEmpty then default else s.last
//   case a: Array[t] => rlastOrElse(if a.isEmpty then default else a.last)
//   case i: Iterable[t] => rlastOrElse(if i.isEmpty then default else i.last)
//   case o: Option[t] => rlastOrElse(o.getOrElse(default))
//   case a: Any => a

def rlastOrElse[X](in: X, default: RElem[X]): RElem[X] = in match
  case s: String => s.lastOption.getOrElse(default)
  case a: Array[t] => rlastOrElse(a.lastOption.getOrElse(default))
  case i: Iterable[t] => rlastOrElse(i.lastOption.getOrElse(default))
  case o: Option[t] => rlastOrElse(o.getOrElse(default))
  case a: Any => a

def rlastOption[X](in: X): Option[RElem[X]] = in match
  case s: String => s.lastOption
  case a: Array[t] => a.lastOption.flatMap(x => rlastOption(x))
  case i: Iterable[t] => i.lastOption.flatMap(x => rlastOption(x))
  case o: Option[t] => o.flatMap(x => rlastOption(x))
  case a: Any => Some(a)

def rlastOrNull[X](in: X): RElem[X] | Null = in match
  case s: String => if s.isEmpty then null else s.last
  case a: Array[t] => if a.isEmpty then null else rlastOrNull(a.last)
  case i: Iterable[t] => if i.isEmpty then null else rlastOrNull(i.last)
  case o: Option[t] => if o.isEmpty then null else rlastOrNull(o.last)
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
