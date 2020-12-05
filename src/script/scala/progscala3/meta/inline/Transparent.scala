// src/script/scala/progscala3/meta/Transparent.scala

trait T:
  def m1: String
  def m2: String = m1

object O extends T:
  inline def m1 = "O.m1"
  override inline def m2 = m1 + " called from O.m2"

