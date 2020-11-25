// src/main/scala/progscala3/contexts/json/JSONBuilder.scala
package progscala3.contexts.json
import scala.annotation.targetName

@main def TryJSONBuilder(): Unit =
  val js = obj {
    "config" -> obj {
      "master" -> obj {
        "host" -> "192.168.1.1"
        "port" -> 8000
        "security" -> "null"
        // "foo" -> (1, 2.2, "three")  // doesn't compile!
      }
      "nodes" -> array {
        aobj {                         // "array object"
          "name" -> "node1"
          "host" -> "192.168.1.10"
        }
        aobj {
          "name" -> "node2"
          "host" -> "192.168.1.20"
        }
        "otherThing" -> 2
      }
    }
  }
  println(js)

object JSONElement:
  def valueString[T](t: T): String = t match                           // <1>
    case "null" => "null"
    case s: String => "\""+s+"\""
    case _ => t.toString

sealed trait JSONElement                                               // <2>
case class JSONKeyedElement[T](key: String, element: T) extends JSONElement:
  override def toString = "\""+key+"\": "+JSONElement.valueString(element)
case class JSONArrayElement[T](element: T) extends JSONElement:
  override def toString = JSONElement.valueString(element)

import scala.collection.mutable.ArrayBuffer

trait JSONContainer(open: String, close: String) extends JSONElement:  // <3>
  val elements = new ArrayBuffer[JSONElement]
  def add(e: JSONElement): Unit = elements += e
  override def toString = elements.mkString(open, ", ", close)

class JSONObject extends JSONContainer("{", "}")
class JSONArray extends JSONContainer("[", "]")

sealed trait ValidJSONValue[T]                                         // <4>
given ValidJSONValue[Int]
given ValidJSONValue[Double]
given ValidJSONValue[String]
given ValidJSONValue[Boolean]
given ValidJSONValue[JSONObject]
given ValidJSONValue[JSONArray]

extension [T : ValidJSONValue] (key: String)                           // <5>
  @targetName("arrow") def ->(element: T)(using jc: JSONContainer) =
    jc.add(JSONKeyedElement(key, element))

def obj(init: JSONObject ?=> Unit) =                                   // <6>
  given jo as JSONObject
  init
  jo

def aobj(init: JSONObject ?=> Unit)(using jc: JSONContainer) =         // <7>
  given jo as JSONObject
  init
  jc.add(jo)

def array(init: JSONArray ?=> Unit) =                                  // <8>
  given ja as JSONArray
  init
  ja
