// src/test/scala/progscala3/dsls/payroll/internal/MoneySuite.scala
package progscala3.dsls.payroll

import progscala3.dsls.payroll.dsc
import munit.FunSuite
import org.scalacheck._
import scala.language.postfixOps

/**
 * ScalaCheck example driven by MUnit
 * TODO: Really this should be a "full" ScalaCheck properties test.
 */

class MoneySuite extends FunSuite:

  val seq1 = Seq("00","10","25","50")

  test("Percentage can be constructed with a floating point literal") {
    val p1: Percentage = 0.25
    assert(p1.equals(Percentage(0.25)))
    val p2: Percentage = 1.123456e3
    assert(p2.equals(Percentage(1.123456e3)))
  }

  test("Percentage.toString returns the value with % appended") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert(s"${i.toInt}.$j%".equals(Percentage(d).toString))
  }

  test("Extension method Double.percent returns a Percentage") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert(d.percent.equals(Percentage(d)))
  }

  test("Dollars can be constructed with a $\"MM.NN\" string") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert($"${i.toInt}.$j".equals(Dollars(d)))
  }

  test("Dollars can be constructed with a floating point literal") {
    val d1: Dollars = 0.25
    assert(d1.equals(Dollars(0.25)))
    val d2: Dollars = 1.123456e3
    assert(d2.equals(Dollars(1.123456e3)))
  }

  test("Dollars.toString returns the value with $ prepended") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert(s"$$${i.toInt}.$j".equals(Dollars(d).toString), s"$$$i.$j =?= ${Dollars(d)}")
  }

  test("Extension method Double.dollars returns a Dollars") {
    for
      i <- seq1
      j <- seq1
      d = i.toDouble + (j.toDouble/100.0)
    do assert(d.dollars.equals(Dollars(d)))
  }
