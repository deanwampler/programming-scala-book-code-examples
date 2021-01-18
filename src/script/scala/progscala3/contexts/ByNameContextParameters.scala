// src/script/scala/progscala3/contexts/ByNameContextParameters.scala

type Status = String                                            // <1>

case class Transaction(database: String):                       // <2>
  def begin(query: String): Status = s"$database: Starting transaction: $query"
  def rollback(): Status = s"$database: Rolling back transaction"
  def commit(): Status = s"$database: Committing transaction"

case class ConnectionManager(database: String):                 // <3>
  println(s"... expensive initialization for database $database")
  def createTransaction: Transaction = Transaction(database)

def doTransaction(query: => String)(                            // <4>
    using cm: => ConnectionManager): Seq[Status] =
  val trans = cm.createTransaction
  Seq(trans.begin(query), trans.commit())

def doPostgreSQL =                                              // <5>
  println("Start of doPostgreSQL.")
  given ConnectionManager = ConnectionManager("PostgreSQL")
  println("Start of doTransaction.")
  doTransaction("SELECT * FROM table")
