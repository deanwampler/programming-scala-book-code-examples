// src/main/scala/Implicits/custom-string-interpolator.sc

import scala.util.parsing.json._

object Interpolators {
  implicit class jsonForStringContext(val sc: StringContext) {
    def json(values: Any*): JSONObject = {            // <1> 
      val keyRE = """^[\s{,]*(\S+):\s*""".r           // <2>
      val keys = sc.parts map {                       // <3> 
        case keyRE(key) => key
        case str => str
      }                
      val kvs = keys zip values                       // <4>
      JSONObject(kvs.toMap)                           // <5>
    }
  }
}

import Interpolators._

val name = "Dean Wampler"
val book = "Programming Scala, Second Edition"

val jsonobj = (json"{name: $name, book: $book}")
println(jsonobj)
