// src/script/scala/progscala3/basicoop/GoodBad.scala

object OBad:
  def m(seq: Seq[Int]): String = seq.mkString("|")
  def m(seq: Seq[String]): String = seq.mkString(",")

trait TGood:
  def member(suffix: String): String
  val member: String

trait TBad:
  def member: String
  val member: String
