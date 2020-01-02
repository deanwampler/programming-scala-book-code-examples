// src/main/scala/progscala3/fp/implicits/implicit-parameter.sc

def multiplier(i: Int)(implicit factor: Int): Int = {
  i * factor
}

implicit val factor = 2

assert(multiplier(2) == 4)
assert(multiplier(2)(3) == 6)
