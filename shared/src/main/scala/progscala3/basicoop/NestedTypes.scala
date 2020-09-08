// src/main/scala/progscala3/basicoop/NestedTypes.scala
package progscala3.basicoop

object Database:                                                     // <1>
  case class ResultSet(/*...*/)                                      // <2>
  case class Connection(/*...*/)

  case class DatabaseException(message: String, cause: Throwable) extends
    RuntimeException(message, cause)

  sealed trait ConnectionState                                       // <3>
  case object Disconnected extends ConnectionState
  case class  Connected(connection: Connection)  extends ConnectionState
  sealed trait QueryStatus
  case class  QuerySucceeded(results: ResultSet) extends QueryStatus
  case class  QueryFailed(e: DatabaseException)  extends QueryStatus

class Database:
  import Database._

  def connect(server: String): ConnectionState = ???                 // <5>
  def disconnect(): ConnectionState = ???

  def query(queryStr: String): QueryStatus = ???
