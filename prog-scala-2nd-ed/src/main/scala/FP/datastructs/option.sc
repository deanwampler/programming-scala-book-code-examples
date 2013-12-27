// src/main/scala/FP/datastructs/option.sc

val someNumber = Some(5)
val noneNumber = None

for (option <- List(noneNumber, someNumber)) {
  option.map(n => println(n * 5))
}
