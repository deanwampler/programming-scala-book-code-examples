// src/main/scala/progscala2/fp/implicits/implicit-conversion.sc
import scala.runtime.RichString

class FancyString(val str: String)

object FancyString2RichString {
    implicit def fancyString2RichString(fs: FancyString) = 
        new RichString(fs.str)
}

import FancyString2RichString._

val fs = new FancyString("scala")
println(fs.capitalize.reverse)
