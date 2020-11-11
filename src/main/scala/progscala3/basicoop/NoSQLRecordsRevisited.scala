// src/main/scala/progscala3/basicoop/NoSQLRecordsRevisited.scala

import scala.util.Try

package progscala3.basicoop:
  package scaladb:
    case class InvalidFieldName(name: String)
      extends RuntimeException(s"Invalid field name $name")

    trait FromTo[T]:                                                 // <1>
      def apply(any: Any): T

    opaque type Record = Map[String,Any]                             // <2>
    extension [T] (rec: Record):                                     // <3>
      def add(nameValue: (String, T))(using FromTo[T]): Record =     // <4>
        rec + nameValue
      def get(colName: String)(using toT: FromTo[T]): Try[T] =       // <5>
        Try(toT(col(colName)))
      private def col(colName: String): Any =
        rec.getOrElse(colName, throw InvalidFieldName(colName))

    object Record:                                                   // <6>
      def empty: Record = Map.empty

    given FromTo[Int]:                                               // <7>
      def apply(any: Any): Int = any.asInstanceOf[Int]
    given FromTo[Double]:
      def apply(any: Any): Double = any.asInstanceOf[Double]
    given FromTo[String]:
      def apply(any: Any): String = any.asInstanceOf[String]

    @main def TryScalaDBRevisited =
      val rec = Record.empty.add("one" -> 1)
        .add("two" -> 2.2).add("three" -> "THREE!")

      val one   = rec.get[Int]("one")
      val two   = rec.get[Double]("two")
      val three = rec.get[String]("three")
      // val four  = rec.get[Byte]("four")                           // <7>
      val bad = rec.get[String]("two")

      println(s"one, two, three -> $one, $two, $three")
      println(s"bad -> $bad")
