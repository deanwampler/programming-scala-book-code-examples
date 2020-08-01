// src/script/scala/progscala3/objectsystem/overrides/FinalMember.scala

class NotFixed:
  final def fixedMethod = "fixed"

class Changeable2 extends NotFixed:
  // Compilation error:
  override def fixedMethod = "not fixed"
