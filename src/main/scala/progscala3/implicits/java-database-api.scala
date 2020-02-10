// src/main/scala/progscala3/implicits/java-database-api.scala

// A Java-like Database API, written in Scala for convenience.
package progscala3.implicits {
  package database_api {

    case class InvalidColumnName(name: String)
      extends RuntimeException(s"Invalid column name $name")

    trait Row {
      def getInt   (colName: String): Int
      def getDouble(colName: String): Double
      def getText  (colName: String): String
    }
  }

  package javadb {
    import database_api._

    case class JRow(representation: Map[String,Any]) extends Row {
      private def col(colName: String): Any =
        representation.getOrElse(colName, throw InvalidColumnName(colName))

      def getInt   (colName: String): Int    = col(colName).asInstanceOf[Int]
      def getDouble(colName: String): Double = col(colName).asInstanceOf[Double]
      def getText  (colName: String): String = col(colName).asInstanceOf[String]
    }

    object JRow {
      def apply(pairs: (String,Any)*) = new JRow(Map(pairs :_*))
    }
  }
}