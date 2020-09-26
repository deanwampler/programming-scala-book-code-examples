// src/main/scala/progscala3/basicoop/NestedTypes.scala
package progscala3.basicoop

object Database:                                                     // <1>
  case class ResultSet(/*...*/)                                      // <2>
  case class Connection(/*...*/)

  case class DatabaseException(message: String, cause: Throwable) extends
    RuntimeException(message, cause)

  enum ConnectionState:                                              // <3>
    case Disconnected
    case Connected(connection: Connection)
  enum QueryStatus:
    case QuerySucceeded(results: ResultSet)
    case QueryFailed(e: DatabaseException)

class Database:
  import Database._

  def connect(server: String): ConnectionState = ???                 // <5>
  def disconnect(): ConnectionState = ???

  def query(queryStr: String): QueryStatus = ???
