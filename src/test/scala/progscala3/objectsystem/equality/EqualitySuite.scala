// src/test/scala/progscala3/objectsystem/equality/EqualitySuite.scala
package progscala3.objectsystem.equality

import munit.*

class EqualitySuite extends FunSuite:

  case class Person(firstName: String, lastName: String, age: Int) derives CanEqual

  val p1a = Person("Dean", "Wampler", 29)
  val p1b = Person("Dean", "Wampler", 29)
  val p2  = Person("Buck", "Trends",  30)

  def testEquals() =
    assert((p1a.equals(p1a))  == true)
    assert((p1a.equals(p1b))  == true)
    assert((p1a.equals(p2))   == false)
    assert((p1a.equals(null)) == false)

  test("The == operator is implemented with the equals method") {
    testEquals()
    assert((p1a == p1a)   == true)
    assert((p1a == p1b)   == true)
    assert((p1a == p2)    == false)
    assert((p1a == null)  == false)
  }

  test("The != operator is implemented with the equals method") {
    assert((p1a != p1a)   == false)
    assert((p1a != p1b)   == false)
    assert((p1a != p2)    == true)
    assert((p1a != null)  == true)
  }

  test("Case classes define equals") {
    testEquals()
  }

  test("null == obj is symmetric with obj == null (both false)") {
    assert((null == p1a) == false)
    assert((p1a == null) == false)
  }

  test("But, null.equals(obj) throws NPE") {
    intercept[NullPointerException] {
      null.equals(p1a)
    }
    intercept[NullPointerException] {
      null.equals(p1a)
    }
  }
  test("But obj.equals(null) doesn't throw NPE, so x.equals(y) is asymmetric") {
    assert((p1a.equals(null)) == false)
  }

  test("null != obj is symmetric with obj != null (both false)") {
    assert(null != p1a)
    assert(p1a  != null)
  }

  test("null == null") {
    assert(null == null)
  }

  test("The eq method compares object identities, not values") {
    assert((p1a.eq(p1a))    == true)
    assert((p1a.eq(p1b))    == false) // But p1a == p1b
    assert((p1a.eq(p2))     == false)
    assert((p1a.eq(null))   == false)
    assert((null.eq(p1a))   == false)
    assert((null.eq(null))  == true)
  }

  test("The eq method returns true when comparing two null references") {
    val n1: String = null
    val n2: String = null
    assert((n1.eq(n2)) == true)
  }

  test("The ne method compares object identities, not values") {
    assert((p1a.ne(p1a))    == false)
    assert((p1a.ne(p1b))    == true)
    assert((p1a.ne(p2))     == true)
    assert((p1a.ne(null))   == true)
    assert((null.ne(p1a))   == true)
    assert((null.ne(null))  == false) // Compiler warns that it's always false.
  }

  test("Equals for sequences") {
    assert((Seq(1, 2) == Seq(1, 2)) == true)
  }

  test("Equals doesn't work for Maps (because they don't derive CanEqual)") {
    assert((Map("one" -> 1, "two" -> 2).equals(
      Map("one" -> 1, "two" -> 2)) == true))
  }

  test("Equals doesn't work for Arrays (because they are defined by Java)") {
    assert((Array(1, 2).equals(Array(1, 2))) == false)
  }

  test("sameElements compare all collections, including Arrays") {
    assert((Seq(1, 2).iterator.sameElements(Seq(1, 2))) == true)
    assert((Array(1, 2).iterator.sameElements(Array(1, 2))) == true)
    assert((Map("one" -> 1, "two" -> 2).iterator.sameElements(
      Map("one" -> 1, "two" -> 2))) == true)
  }
end EqualitySuite
