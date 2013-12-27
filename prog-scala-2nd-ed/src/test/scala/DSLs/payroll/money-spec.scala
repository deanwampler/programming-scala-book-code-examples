// src/test/scala/DSLs/payroll/money-spec.scala

package dsls.payroll

import org.scalatest.{ FunSpec, ShouldMatchers } 
import org.scalatest.prop.Checkers
import org.scalacheck.Arbitrary._
import org.scalacheck.Prop._

class MoneySpec extends FunSpec with ShouldMatchers 
  with Checkers with ArbitraryMoney { 
  def monoidCheck(sym: String, name: String,
    bdop: (BigDecimal,BigDecimal) => BigDecimal, mop: (Money,Money) => Money) {
    
    describe(sym) {
      it (s"$name the amounts") {
        check ((a: Money, b: Money) => mop(a,b) == Money(bdop(a.amount, b.amount)))
      }
      it ("is pairwise groupable") {
        check { (a: Money, b: Money, c: Money) => 
          mop(mop(a, b), c) == mop(Money(bdop(a.amount, b.amount)), Money(c.amount)) &&
          mop(a, mop(b, c)) == mop(Money(a.amount), Money(bdop(b.amount, c.amount)))
        }
      }
      it ("is commutative") {
        check ((a: Money, b: Money) => mop(a, b) == mop(b, a))
      }
      it ("is associative") {
        check ((a: Money, b: Money, c: Money) => mop(mop(a, b), c) == mop(a, mop(b, c)))
      }
    }
  }

  private def commutativeCheck(isCommutative: Boolean, sym: String,
    bdop: (BigDecimal,BigDecimal) => Boolean, mop: (Money,Money) => Boolean) {

    describe (sym) { 
      it ("compares the two amounts") {
        check ((a: Money, b: Money) => mop(a, b) == bdop(a.amount,  b.amount))
      }
      if (isCommutative) {
        it ("is commutative") {
          check ((a: Money, b: Money) => a == b || mop(a, b) == mop(b, a)) 
        }
      } else {
        it ("is anti-commutative") {
          check ((a: Money, b: Money) => a == b || mop(a, b) != mop(b, a))
        }
      }
    }
  }

  describe ("Money") {
    monoidCheck("+", "adds", (d1,d2) => d1 + d2, (m1,m2) => m1 + m2)
    monoidCheck("*", "multiplies", (d1,d2) => d1 * d2, (m1,m2) => m1 * m2)
  
    describe("-") { 
      it ("subtracts the amounts") {
        check((a: Money, b: Money) => (a - b) == Money(a.amount - b.amount))
      }
      it ("is pairwise groupable") {
        check { (a: Money, b: Money, c: Money) => 
          ((a - b) - c) == (Money(a.amount - b.amount) - Money(c.amount)) &&
          (a - (b - c)) == (Money(a.amount) - Money(b.amount - c.amount))
        }
      }
      it ("is anti-commutative") {
        check ((a: Money, b: Money) => (a - b) == -(b - a))
      }
      it ("is not associative") {
        check ((a: Money, b: Money, c: Money) => ((a - b) - c) == (a - (b + c)))
      }
    }
  
    // Hack: Avoid 1/0, by shortcircuiting to true when nearZero returns true.
    // A better approach would be a custom example generator that avoids
    // values near zero.
    def nearZero(m: Money): Boolean = m.amount <= 0.0001

    describe("/") { 
      it ("is undefined if you divide by zero!") {
        check((a: Money, b: Money) => nearZero(b) || (a * b) == Money(a.amount*b.amount))
      }
      it ("divides the amounts") {
        check((a: Money, b: Money) => nearZero(b) || (a / b) == Money(a.amount / b.amount))
      }
      it ("is pairwise groupable") {
        check { (a: Money, b: Money, c: Money) => 
          nearZero(b) || nearZero(c) || 
          ((a / b) / c) == (Money(a.amount / b.amount) / Money(c.amount)) &&
          (a / (b / c)) == (Money(a.amount) / Money(b.amount / c.amount))
        }
      }
      it ("is not commutative") {
        check { (a: Money, b: Money) => 
          a == b || nearZero(a) || nearZero(b) || (a / b) != (b / a)
        }
      }
      it ("is not associative") {
        check { (a: Money, b: Money, c: Money) => 
          (a == b && b == c) || nearZero(b) || nearZero(c) || 
          ((a / b) / c) != (a / (b + c))
        }
      }
    }

    List(
      ("<",  false, (d1:BigDecimal, d2:BigDecimal) => d1 <  d2, (m1:Money, m2:Money) => m1 <  m2),
      ("<=", false, (d1:BigDecimal, d2:BigDecimal) => d1 <= d2, (m1:Money, m2:Money) => m1 <= m2),
      (">",  false, (d1:BigDecimal, d2:BigDecimal) => d1 >  d2, (m1:Money, m2:Money) => m1 >  m2),
      (">=", false, (d1:BigDecimal, d2:BigDecimal) => d1 >= d2, (m1:Money, m2:Money) => m1 >= m2),
      ("==", true,  (d1:BigDecimal, d2:BigDecimal) => d1 == d2, (m1:Money, m2:Money) => m1 == m2),
      ("!=", true,  (d1:BigDecimal, d2:BigDecimal) => d1 != d2, (m1:Money, m2:Money) => m1 != m2)
    ) foreach { case (sym, tf, bdop, mop) => 
      commutativeCheck(tf, sym, bdop, mop)
    }
  }
}
