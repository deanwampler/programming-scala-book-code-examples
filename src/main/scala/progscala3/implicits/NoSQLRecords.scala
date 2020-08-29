// src/main/scala/progscala3/implicits/NoSQLRecords.scala

import scala.util.Try

package progscala3.implicits:
  package scaladb:
    case class InvalidFieldName(name: String)
      extends RuntimeException(s"Invalid field name $name")

    trait FromTo[T]:                                                 // <1>
      def apply(any: Any): T

    case class Record(contents: Map[String,Any] = Map.empty):        // <2>
      def add[T](nameValue: (String, T))(using FromTo[T]): Record =  // <3>
        Record(contents + nameValue)
      def get[T](colName: String)(using toT: FromTo[T]): Try[T] =    // <4>
        Try(toT(col(colName)))
      private def col(colName: String): Any =
        contents.getOrElse(colName, throw InvalidFieldName(colName))

    object Record:                                                   // <5>
      def make: Record = new Record()

    given FromTo[Int]:                                               // <6>
      def apply(any: Any): Int = any.asInstanceOf[Int]
    given FromTo[Double]:
      def apply(any: Any): Double = any.asInstanceOf[Double]
    given FromTo[String]:
      def apply(any: Any): String = any.asInstanceOf[String]

    @main def TryScalaDB =
      val rec = Record.make.add("one" -> 1)
        .add("two" -> 2.2).add("three" -> "THREE!")

      val one   = rec.get[Int]("one")
      val two   = rec.get[Double]("two")
      val three = rec.get[String]("three")
      // val four  = rec.get[Byte]("four")                           // <7>
      val bad = rec.get[String]("two")

      println(s"one, two, three -> $one, $two, $three")
      println(s"bad -> $bad")
