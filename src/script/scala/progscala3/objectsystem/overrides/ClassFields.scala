// src/script/scala/progscala3/objectsystem/overrides/ClassFields.scala

trait T5:
  val name = "T5"
  var count = 0

class ClassT5 extends T5:
  override val name = "ClassT5"
  count = 1

val c = new ClassT5()
assert(c.name == "ClassT5")
assert(c.count == 1)
