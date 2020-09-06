// src/main/scala/progscala3/implicits/json/JSONBuilder.scala
package progscala3.implicits.json

@main def TryJSONBuilder(): Unit =
  val js = map {
    "config" -> map {
      "master" -> map {
        "host" -> "192.168.1.1"
        "port" -> 8000
        "security" -> "null"
        // "foo" -> (1, 2.2, "three")  // doesn't compile!
      }
      "nodes" -> array {
        elem {
          "name" -> "node1"
          "host" -> "192.168.1.10"
        }
        elem {
          "name" -> "node2"
          "host" -> "192.168.1.20"
        }
      }
    }
  }
  println(js)

object JSONElement:                                                  // <1>
  def valueString[T](t: T): String = t match
    case "null" => "null"
    case s: String => "\""+s+"\""
    case _ => t.toString

sealed trait JSONElement                                             // <2>
case class JSONNamedElement[T](name: String, element: T) extends JSONElement:
  override def toString = "\""+name+"\": "+JSONElement.valueString(element)
case class JSONArrayElement[T](element: T) extends JSONElement:
  override def toString = JSONElement.valueString(element)

import scala.collection.mutable.ArrayBuffer

trait JSONContainer extends JSONElement:                             // <3>
  val elements = new ArrayBuffer[JSONElement]
  def add(e: JSONElement): Unit = elements += e
  def open: String
  def close: String
  override def toString = elements.mkString(open, ", ", close)

class JSONObject extends JSONContainer:
  val open: String  = "{"
  val close: String = "}"
class JSONArray extends JSONContainer:
  val open: String  = "["
  val close: String = "]"

sealed trait ValidJSONValue[T]                                       // <4>
implicit object VJSONInt     extends ValidJSONValue[Int]
implicit object VJSONDouble  extends ValidJSONValue[Double]
implicit object VJSONString  extends ValidJSONValue[String]
implicit object VJSONBoolean extends ValidJSONValue[Boolean]
implicit object VJSONObject  extends ValidJSONValue[JSONObject]
implicit object VJSONArray   extends ValidJSONValue[JSONArray]

extension [T : ValidJSONValue] (name: String)                        // <5>
  def ->(element: T)(using jc: JSONContainer) =
    jc.add(JSONNamedElement(name, element))

def map(init: JSONObject ?=> Unit) =                                 // <6>
  given jo as JSONObject
  init
  jo

def elem(init: JSONObject ?=> Unit)(using jc: JSONContainer) =       // <7>
  given jo as JSONObject
  init
  jc.add(jo)

def array(init: JSONArray ?=> Unit) =                                // <8>
  given ja as JSONArray
  init
  ja
