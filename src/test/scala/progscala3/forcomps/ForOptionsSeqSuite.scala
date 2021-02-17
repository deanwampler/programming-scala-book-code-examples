// src/test/scala/progscala3/forcomps/ForOptionsSeqSuite.scala
package progscala3.forcomps

import munit.*

class ForOptionsSeqSuite extends FunSuite:
  val options: Seq[Option[Int]] = Vector(Some(10), None, Some(20))

  test("for comprehension over a sequence of options removes Nones") {
    val results = for
      case Some(i) <- options
    yield (2 * i)
    assert(results == Vector(20, 40))
  }

  test("for comprehension None filtering uses withFilter") {
    val results = for
      case Some(i) <- options withFilter {
        case Some(i) => true
        case None => false
      }
    yield (2 * i)
    assert(results == Vector(20, 40))
  }

  test("for comprehensions with yield do mapping") {
    val results = options withFilter {
      case Some(i) => true
      case None => false
    } map {
      case Some(i) => (2 * i)
      case None => -1             // <1>
    }
    assert(results == Vector(20, 40))
  }
