// src/main/scala/progscala3/contexts/json/JSONBuilder.scala
package progscala3.contexts.json
import scala.annotation.targetName

/**
 * This example is mentioned in the book, but omitted from it because of its
 * length and details. It is inspired by the Dotty reference page for context
 * functions, https://dotty.epfl.ch/docs/reference/contextual/context-functions.html.
 * We'll create a _domain-specific language_ (DSL) for constructing JSON. For
 * simplicity, we won't construct instances for some JSON library, just
 * JSON-formatted strings. To motivate the example, let's begin with an entry
 * point that shows the DSL in action.
 * If you run this in SBT `> runMain progscala3.contexts.json.TryJSONBuilder`
 * You'll get output like the following, but I've formatted it nicer here:
 * ```
 * {
 *   "config": {
 *     "master": {
 *       "host": "192.168.1.1", "port": 8000, "security": null
 *     },
 *     "nodes": [
 *       {"name": "node1", "host": "192.168.1.10"},
 *       {"name": "node2", "host": "192.168.1.20"},
 *       "otherThing" -> 2
 *     ]
 *   }
 * }
 * ```
 */
@main def TryJSONBuilder(): Unit =
  import JSONBuilder.*
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
end TryJSONBuilder

/** Implement the JSON Builder DSL. */
object JSONBuilder:
  /**
   * Constrct the correct string representation for a value.
   * JSON allows `null`s, for which we'll expect the user to use the string "null"
   * (as shown in the example). Hence, `valueString` returns `null` without quotes,
   * all other strings are returned in double quotes, and for everything else, the
   * output of `toString` is used.
   .*/
  object JSONElement:
    def valueString[T <: Matchable](t: T): String = t match
      case "null" => "null"
      case s: String => "\""+s+"\""
      case _ => t.toString

  /**
   * We can model everything as either a "keyed" element of the form `"key": value`
   * or just a value, but the latter cases only appear as elements in arrays!
   */
  sealed trait JSONElement
  case class JSONKeyedElement[T <: Matchable](
      key: String, element: T) extends JSONElement:
    override def toString = "\""+key+"\": "+JSONElement.valueString(element)
  case class JSONArrayElement[T <: Matchable](
      element: T) extends JSONElement:
    override def toString = JSONElement.valueString(element)

  import scala.collection.mutable.ArrayBuffer

  /**
   * For both JSON objects and arrays, we add elements to a mutable array buffer.
   * There are two places the `add` method is called, discussed below.
   * For our purposes, only the opening and closing delimiters differ between
   * objects and arrays. The concrete classes `JSONObject` and `JSONArray` define
   * the delimiters.
   */
  trait JSONContainer(open: String, close: String) extends JSONElement:
    val elements = ArrayBuffer[JSONElement]()
    def add(e: JSONElement): Unit = elements += e
    override def toString = elements.mkString(open, ", ", close)

  class JSONObject extends JSONContainer("{", "}")
  class JSONArray extends JSONContainer("[", "]")

  /**
   * This sealed trait and the following given instances of `ValidJSONValue[T]`
   * are _witnesses_, constraining the allowed types of JSON values. Note that
   * there is nothing to implement in the trait, but we have to use the `with {}`
   * clauses to make these definitions concrete.
   */
  sealed trait ValidJSONValue[T <: Matchable]
  given ValidJSONValue[Int] with {}
  given ValidJSONValue[Double] with {}
  given ValidJSONValue[String] with {}
  given ValidJSONValue[Boolean] with {}
  given ValidJSONValue[JSONObject] with {}
  given ValidJSONValue[JSONArray] with {}


  /**
   * This `String` extension method is constrained by `ValidJSONValue[T]` concrete
   * instances. It constructs `JSONKeyedElement`s using `"key" -> value`, just
   * like tuple pairs, but constrained by the context bound `T : ValidJSONValue`.
   * We use a `JSONContainer` because these key-value pairs only occur inside
   * containers (objects or arrays) in the DSL. It is here that we add the
   * key-value pairs to the container `jc`. Note that this definition of `->` will
   * shadow the generic `ArrowAssoc` implementation for constructing tuples!
   */
  extension (key: String)
    @targetName("arrow") def ->[T <: Matchable : ValidJSONValue](
        element: T)(using jc: JSONContainer) =
      jc.add(JSONKeyedElement(key, element))

  /**
   * A whole JSON object, as well as nested objects, starts with `obj`. Refer to
   * the example above in `TryJSONBuilder`. Where does the `init` context function
   * of type `JSONObject ?=> Unit` come from? It is constructed by the compiler
   * from the expressions inside the braces passed as the argument to `obj`.
   * Or, as it appears in the DSL, the braces after the `obj` "keyword". Next,
   * the `given` clause creates an instance of `JSONObject` named `jo`. Then,
   * `init` is evaluated, where `jo` will be used to satisfy using clauses inside
   * those nested expressions. Finally, we return `jo`.
   */
  def obj(init: JSONObject ?=> Unit) =
    given jo: JSONObject = JSONObject()
    init
    jo

  /**
   * Use `aobj` to define objects as array elements. Note that this function
   * has a using clause (unlike `obj`), which will always be a `JSONArray`.
   * Unfortunately, the name `obj` can't be overloaded here, because the compiler
   * would consider the two definitions ambiguous. The body of `aobj` is the
   * second place where the `add` method is called. Recall that the other location
   * is inside the `String` extension method `->`.
   */
  def aobj(init: JSONObject ?=> Unit)(using jc: JSONContainer) =
    given jo: JSONObject = JSONObject()
    init
    jc.add(jo)

  /**
   * Define an array. This body is very similar to `obj`.
   */
  def array(init: JSONArray ?=> Unit) =
    given ja: JSONArray = JSONArray()
    init
    ja
end JSONBuilder
