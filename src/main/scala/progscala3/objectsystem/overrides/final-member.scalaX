// src/main/scala/progscala3/objectsystem/overrides/final-member.scalaX
package progscala3.objectsystem.overrides

class NotFixed {
  final def fixedMethod = "fixed"
}

class Changeable2 extends NotFixed {
  override def fixedMethod = "not fixed"   // COMPILATION ERROR
}
