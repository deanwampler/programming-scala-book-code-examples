// src/main/scala/progscala3/basicoop/ValueClassDollar.scala
package progscala3.basicoop

class Dollar(val value: Float) extends AnyVal:        // <1>
  override def toString = "$%.2f".format(value)
