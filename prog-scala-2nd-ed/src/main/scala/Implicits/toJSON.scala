// src/main/scala/Implicits/toJSON.scala

// For simplicity, just output Strings, not actual JSON...
trait JSONSerializer {
  def toJSON: String
}

object Implicits {
  implicit class ProductJSONSerializer(p: Product) extends JSONSerializer {
    def toJSON:String = """${p.productPrefix}: {
      |  name =   
      |}""".stripMargin
  }
}

import Implicits._

val m = Map("one" <-*-> 1, "two" <-*-> 2)
