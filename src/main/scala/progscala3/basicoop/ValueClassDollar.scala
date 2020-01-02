// src/main/scala/progscala3/basicoop/ValueClassDollar.sc

/** 
 * Real money classes shouldn't use Floats!
 */
class Dollar(val value: Float) extends AnyVal {
  override def toString = "$%.2f".format(value)
}
