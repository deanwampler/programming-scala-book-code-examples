// src/main/scala/progscala3/implicits/ScalaDatabaseApi.scala

// A Scala wrapper for the Java-like Database API.
package progscala3.implicits:
  package scaladb:
    object implicits:
      import javadb.JRow

      implicit class SRow(jrow: JRow):
        def get[T](colName: String)(implicit toT: (JRow,String) => T): T =
          toT(jrow, colName)

      implicit val jrowToInt: (JRow,String) => Int =
        (jrow: JRow, colName: String) => jrow.getInt(colName)
      implicit val jrowToDouble: (JRow,String) => Double =
        (jrow: JRow, colName: String) => jrow.getDouble(colName)
      implicit val jrowToString: (JRow,String) => String =
        (jrow: JRow, colName: String) => jrow.getText(colName)


    @main def TryScalaDB =
      import implicits._
      val row = javadb.JRow("one" -> 1, "two" -> 2.2, "three" -> "THREE!")

      val oneValue1: Int      = row.getInt("one")
      val twoValue1: Double   = row.getDouble("two")
      val threeValue1: String = row.getText("three")
      // val fourValue1: Byte    = row.get("four")  // won't compile

      println(s"one1   -> $oneValue1")
      println(s"two1   -> $twoValue1")
      println(s"three1 -> $threeValue1")

      val oneValue2   = row.get[Int]("one")
      val twoValue2   = row.get[Double]("two")
      val threeValue2 = row.get[String]("three")
      // val fourValue2    = row.get[Byte]("four")  // won't compile

      println(s"one2   -> $oneValue2")
      println(s"two2   -> $twoValue2")
      println(s"three2 -> $threeValue2")
