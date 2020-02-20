// src/main/scala/progscala3/forcomps/ForOptionsGood.scala
package progscala3.forcomps

object ForOptionsGood {

  def positive(i: Int): Option[Int] =
    if (i > 0) Some(i) else None

  def main(args: Array[String]): Unit = {
  	val result1 = for {
  	  i1 <- positive(5)
  	  i2 <- positive(10 * i1)
  	  i3 <- positive(25 * i2)
  	  i4 <- positive(2  * i3)
  	} yield (i1 + i2 + i3 + i4)
  	assert(result1 == Some(3805))

  	val result2 = for {
  	  i1 <- positive(5)
  	  i2 <- positive(-1 * i1)              // <1>   EPIC FAIL!
  	  i3 <- positive(25 * i2)              // <2>
  	  i4 <- positive(-2 * i3)              // EPIC FAIL!
  	} yield (i1 + i2 + i3 + i4)
  	assert(result2 == None)
  }
}
