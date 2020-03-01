// src/main/scala/progscala3/objectsystem/overrides/FinalClass.scala
package progscala3.objectsystem.overrides

final class Fixed {
  def doSomething = "Fixed did something!"
}

// Would cause a compilation error:
// class Changeable1 extends Fixed
