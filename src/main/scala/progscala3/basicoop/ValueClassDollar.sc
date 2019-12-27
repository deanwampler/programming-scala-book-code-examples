// src/main/scala/progscala3/basicoop/ValueClassDollar.sc

class Dollar(val value: Float) extends AnyVal {
  override def toString = "$%.2f".format(value)
}

val benjamin = new Dollar(100)
println(benjamin)
// Result: benjamin: Dollar = $100.00