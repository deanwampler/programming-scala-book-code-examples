// src/main/scala/ForComps/option.sc

val list: List[Option[Int]] = List(Some(10), None, Some(20))

for {
  opt <- list
  i2  <- opt map (x => 2 * x)
} yield i2

for {
  opt <- list
  i2  <- opt flatMap (x => Some(2 * x))
} yield i2
