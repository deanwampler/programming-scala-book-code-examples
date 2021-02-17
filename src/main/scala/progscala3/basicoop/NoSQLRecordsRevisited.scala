// src/main/scala/progscala3/basicoop/NoSQLRecordsRevisited.scala
package progscala3.basicoop.scaladb

import scala.util.Try

case class InvalidFieldName(name: String)
  extends RuntimeException(s"Invalid field name $name")

trait FromTo[T]:
  def apply(any: Any): T

opaque type Record = Map[String,Any]
extension (rec: Record)
  def add[T : FromTo](nameValue: (String, T)): Record =
    rec + nameValue
  def get[T : FromTo](colName: String): Try[T] =
    Try(summon[FromTo[T]](col(colName)))
  private def col(colName: String): Any =
    rec.getOrElse(colName, throw InvalidFieldName(colName))

object Record:
  def empty: Record = Map.empty

given FromTo[Int] with
  def apply(any: Any): Int = any.asInstanceOf[Int]
given FromTo[Double] with
  def apply(any: Any): Double = any.asInstanceOf[Double]
given FromTo[String] with
  def apply(any: Any): String = any.asInstanceOf[String]

@main def TryScalaDBRevisited =
  val rec = Record.empty.add("one" -> 1)
    .add("two" -> 2.2).add("three" -> "THREE!")

  val one   = rec.get[Int]("one")
  val two   = rec.get[Double]("two")
  val three = rec.get[String]("three")
  // val four  = rec.get[Byte]("four")
  val bad = rec.get[String]("two")

  println(s"one, two, three -> $one, $two, $three")
  println(s"bad -> $bad")
