// src/script/scala/progscala3/rounding/TypeErasureTargetNameFix.scala

import scala.annotation.targetName
object O:
  @targetName("m_seq_int")
  def m(is: Seq[Int]): Int = is.sum
  @targetName("m_seq_string")
  def m(ss: Seq[String]): Int = ss.length
