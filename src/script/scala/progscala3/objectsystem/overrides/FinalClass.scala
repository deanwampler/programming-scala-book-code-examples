// src/main/scala/progscala3/objectsystem/overrides/FinalClass.scala

final class Fixed:
  def doSomething = "Fixed did something!"

// Compilation error:
class Changeable1 extends Fixed
