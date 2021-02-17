// src/test/scala/progscala3/forcomps/ForOptionsBad2Suite.scala
package progscala3.forcomps

import munit.*

/** Another "bad" example, when easier idioms exist. .*/
class ForOptionsBad2Suite extends FunSuite:
  def sumCountsBad(counts: Seq[Option[Int]]): Option[Int] =
    (counts foldLeft Option(0)) {
      case (None, count) => None
      case (countOption, None) => None
      case (countOption, count) => Some(countOption.get + count.get)
    }

  test("Working code with Options that is unnecessarily verbose") {
    val successfulCounts = Seq(Some(5), Some(21), Some(8))
    val partiallySuccessfulCounts = Seq(Some(5), None, Some(8))

    assert(sumCountsBad(successfulCounts) == Some(34))
    assert(sumCountsBad(partiallySuccessfulCounts) == None)
  }
