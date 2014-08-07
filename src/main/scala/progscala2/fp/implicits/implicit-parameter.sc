// src/main/scala/progscala2/fp/implicits/implicit-parameter.sc
import scala.runtime.RichString

def multiplier(i: Int)(implicit factor: Int) {
  println(i * factor)
}

implicit val factor = 2

multiplier(2)
multiplier(2)(3)
