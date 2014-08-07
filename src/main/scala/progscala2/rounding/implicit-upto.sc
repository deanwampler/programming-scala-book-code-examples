// src/main/scala/progscala2/rounding/implicit-upto.sc

implicit class IntUpTo(i: Int) {
  def upto(j: Int) = i to j
}

for (i <- 1 upto 10) println(i)
