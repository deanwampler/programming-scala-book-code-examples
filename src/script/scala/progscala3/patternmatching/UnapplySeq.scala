// src/script/scala/progscala3/patternmatching/UnapplySeq.scala

object Tokenize:
  // def unapplySeq(s: String): Option[Seq[String]] = Some(tokenize(s))   // <1>
  def unapplySeq(lim_s: (Int,String)): Option[Seq[String]] =              // <2>
    val (limit, s) = lim_s
    if limit > s.length then None
    else
      val seq = tokenize(s).filter(_.length >= limit)
      Some(seq)

  def tokenize(s: String): Seq[String] = s.split("""\W+""").toSeq         // <3>

val message = "This is Programming Scala v3"
val limits = Seq(1, 3, 20, 100)

val results = for limit <- limits yield (limit, message) match
  case Tokenize() => s"No words of length >= $limit!"
  case Tokenize(a, b, c, d*) => s"limit: $limit => $a, $b, $c, d=$d"      // <4>
  case x => s"limit: $limit => Tokenize refused! x=$x"

assert(results == Seq(
  "limit: 1 => This, is, Programming, d=ArraySeq(Scala, v3)",
  "limit: 3 => This, Programming, Scala, d=ArraySeq()",
  "No words of length >= 20!",
  "limit: 100 => Tokenize refused! x=(100,This is Programming Scala v3)"))
