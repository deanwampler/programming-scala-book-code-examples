// src/main/scala/progscala2/appdesign/annotations/anno-example.scala

package progscala2.appdesign.annotations
import scala.annotation.StaticAnnotation

class Persist(tableName: String, params: Map[String,Any]) 
  extends StaticAnnotation

// Doesn't compile:
//@Persist("ACCOUNTS", Map("dbms" -> "MySql", "writeAutomatically" -> true))
@Persist("ACCOUNTS", Map(("dbms", "MySql"), ("writeAutomatically", true)))
class Account(val balance: Double)
