// src/main/scala/progscala2/basicoop/ValueClassDollar.sc

class Dollar(val value: Float) extends AnyVal {
  override def toString = "$%.2f".format(value)
}

val benjamin = new Dollar(100)
// Result: benjamin: Dollar = $100.00