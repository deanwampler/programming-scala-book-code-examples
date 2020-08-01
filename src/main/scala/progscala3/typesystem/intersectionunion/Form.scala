// src/main/scala/progscala3/typesystem/intersectionunion/Linearization.scala
package progscala3.typesystem.intersectionunion
import scala.util.Try

trait JSONConvertable:
	def json(): String  // Just return JSON string for simplicity

trait Persistable:
	def persist(): Try[Boolean]

class Form(fields: Map[String,Any]) extends Persistable with JSONConvertable:
	def json(): String = ???
	def persist(): Try[Boolean] = ???
