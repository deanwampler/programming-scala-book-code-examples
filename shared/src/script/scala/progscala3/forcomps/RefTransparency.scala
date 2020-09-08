// src/script/scala/progscala3/forcomps/RefTransparency.scala

def addInts(s1: String, s2: String): Int = s1.toInt + s2.toInt

def addInts2(s1: String, s2: String): Either[String,Int] =
  try
    Right(s1.toInt + s2.toInt)
  catch
    case nfe: NumberFormatException => Left("NFE: "+nfe.getMessage)

val add12a = addInts("1", "2")
val add12b = addInts2("1", "2")

val add1x  = addInts2("1", "x")
val addx2  = addInts2("x", "2")
val addxy  = addInts2("x", "y")
