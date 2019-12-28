// src/main/scala/progscala3/typesystem/dependentmethodtypes/dep-method.sc
sealed trait Sumable {
  type Element
  def sum: Element
}

case class IntSumable(values: Seq[Int]) extends Sumable {
  type Element = Int
  val sum: Int = values.foldLeft(0)(_+_)
}
case class StringSumable(values: Seq[String]) extends Sumable {
  type Element = String
  val sum: String = {
    values.foldLeft(new StringBuilder()) {
      (sb, s) => sb.append(s)
    }.toString
  }
}

object SummingService {
  def sum(sumable: Sumable): sumable.Element = {
    sumable.sum
  }
}


println("IntSumable:")
val is = IntSumable(0 until 10)
assert(SummingService.sum(is) == 45)

println("StringSumable:")
val ss = StringSumable((0 until 10).map(_.toString))
assert(SummingService.sum(ss) == "0123456789")
