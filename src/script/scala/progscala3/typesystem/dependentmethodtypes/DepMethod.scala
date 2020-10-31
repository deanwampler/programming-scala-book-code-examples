// src/script/scala/progscala3/typesystem/dependentmethodtypes/DepMethod.scala
trait Sum:
  type Element
  def sum(values: Seq[Element]): Element

object IntSum extends Sum:
  type Element = Int
  def sum(values: Seq[Int]): Int = values.foldLeft(0)(_+_)

object StringSum extends Sum:
  type Element = String
  def sum(values: Seq[String]): String =
    values.foldLeft(new StringBuilder()) {
      (sb, s) => sb.append(s)
    }.toString

assert(IntSum.sum(0 until 10) == 45)
assert(StringSum.sum((0 until 10).map(_.toString)) == "0123456789")
