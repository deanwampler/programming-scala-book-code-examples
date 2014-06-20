// src/main/scala/BasicOOP/nested-types.sc

object Database {                                          // <1>
  case class ResultSet(/*...*/)                            // <2>
  case class Connection(/*...*/)                           // <3>

  case class DatabaseException(message: String, cause: Throwable) extends
    RuntimeException(message, cause)

  trait Status
  case object Disconnected extends Status                  // <4>
  case class  Connected(connection: Connection)  extends Status
  case class  QuerySucceeded(results: ResultSet) extends Status
  case class  QueryFailed(e: DatabaseException)  extends Status
}

class Database {
  import Database._
  
  def connect(server: String): Status = ???                // <5>
  def disconnect(): Status = ???

  def query(/*...*/): Status = ???
}
