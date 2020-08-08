// src/script/scala/progscala3/rounding/LazyInitVal.scala

case class DBConnection():
  println("In constructor")
  type MySQLConnection = String
  lazy val connection: MySQLConnection =
    // Connect to the database
    println("Connected!")
    "DB"

val dbc = DBConnection()
dbc.connection
dbc.connection
