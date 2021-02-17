// src/test/scala/progscala3/contexts/SQLStringInterpolatorSuite.scala
package progscala3.contexts

import munit.*

class SQLStringInterpolatorSuite extends FunSuite:
  import SimpleSQL.*

  test("""A custom interpolator is invoked with name"" """) {
    val query = sql"SELECT one, two FROM t"
    assert(query == SQLQuery(Vector("one", "two"), "t"), query.toString)
  }

  test("A custom interpolator can use variable interpolation.") {
    val cols = Seq("one", "two", "three")
    val table = "t1"
    val query = sql"SELECT ${cols.mkString(",")} FROM $table"
    assert(query == SQLQuery(Vector("one", "two", "three"), "t1"), query.toString)
  }

  test("A custom interpolator can use variable interpolation.") {
    val cols = Seq("one", "two", "three")
    val query = sql"SELECT ${cols.mkString(",")} FROM t2"
    assert(query == SQLQuery(Vector("one", "two", "three"), "t2"), query.toString)
  }

  test("A custom interpolator can use variable interpolation.") {
    val table = "t3"
    val query = sql"SELECT a, b, c FROM $table"
    assert(query == SQLQuery(Vector("a", "b", "c"), "t3"), query.toString)
  }

  test("The query ends with an optional semicolon") {
    val query1 = sql"SELECT one, two FROM t4;"
    assert(query1 == SQLQuery(Vector("one", "two"), "t4"), query1.toString)
    val table = "t4"
    val query2 = sql"SELECT a, b, c FROM $table"
    assert(query2 == SQLQuery(Vector("a", "b", "c"), "t4"), query2.toString)
  }

  test("The * can be used for the columns") {
    val query1 = sql"SELECT * FROM t5;"
    assert(query1 == SQLQuery(Vector("*"), "t5"), query1.toString)
    val cols = Seq("*")
    val query2 = sql"SELECT ${cols.mkString(",")} FROM t5"
    assert(query2 == SQLQuery(Vector("*"), "t5"), query2.toString)
  }
