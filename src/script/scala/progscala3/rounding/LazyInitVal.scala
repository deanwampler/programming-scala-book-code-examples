// src/script/scala/progscala3/rounding/LazyInitVal.scala

case class DBConnection(connection: String):   // Simplified example
	type MySQLConnection = String
  lazy val connection: MySQLConnection =
    // Connect to the database
    "Connected!"
