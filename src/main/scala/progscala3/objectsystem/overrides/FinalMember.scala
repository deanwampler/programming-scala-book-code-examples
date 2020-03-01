// src/main/scala/progscala3/objectsystem/overrides/FinalMember.scala
package progscala3.objectsystem.overrides

class NotFixed {
  final def fixedMethod = "fixed"
}

class Changeable2 extends NotFixed {
  override def fixedMethod = "not fixed"   // COMPILATION ERROR
}
