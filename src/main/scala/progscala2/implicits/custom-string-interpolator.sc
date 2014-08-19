// src/main/scala/progscala2/implicits/custom-string-interpolator.sc
import scala.util.parsing.json._

object Interpolators {
  implicit class jsonForStringContext(val sc: StringContext) {       // <1>
    def json(values: Any*): JSONObject = {                           // <2>
      val keyRE = """^[\s{,]*(\S+):\s*""".r                          // <3>
      val keys = sc.parts map {                                      // <4>
        case keyRE(key) => key
        case str => str
      }
      val kvs = keys zip values                                      // <5>
      JSONObject(kvs.toMap)                                          // <6>
    }
  }
}

import Interpolators._

val name = "Dean Wampler"
val book = "Programming Scala, Second Edition"

val jsonobj = json"{name: $name, book: $book}"                       // <7>
println(jsonobj)
