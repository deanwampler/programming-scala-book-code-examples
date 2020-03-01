// src/main/scala/progscala3/objectsystem/overrides/final-class.scalaX
package progscala3.objectsystem.overrides

final class Fixed {
  def doSomething = "Fixed did something!"
}

class Changeable1 extends Fixed     // COMPILATION ERROR
