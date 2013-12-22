// code-examples/DSLs/payroll/money-spec.scala
package dsls.payroll

import org.scalatest.{ FunSpec, ShouldMatchers } 
import org.scalacheck._
import org.scalacheck.Prop._

class MoneySpec extends FunSpec with ShouldMatchers
        with ScalaCheck with ArbitraryMoney { 

    "Money +" verifies { 
        (a: Money, b: Money) => (a + b) == Money(a.amount + b.amount)
    }
    "Money -" verifies { 
        (a: Money, b: Money) => (a - b) == Money(a.amount - b.amount)
    }
    "Money *" verifies { 
        (a: Money, b: Money) => (a * b) == Money(a.amount * b.amount)
    }
    "Money *" verifies { 
        // Don't divide by zero!
        (a: Money, b: Money) => b == 0.0 || (a * b) == Money(a.amount*b.amount)
    }

    "Money <" verifies { 
        (a: Money, b: Money) => (a < b)  == (a.amount <  b.amount)
    }
    "Money <=" verifies { 
        (a: Money, b: Money) => (a <= b) == (a.amount <= b.amount)
    }
    "Money >" verifies { 
        (a: Money, b: Money) => (a > b)  == (a.amount >  b.amount)
    }
    "Money >=" verifies { 
        (a: Money, b: Money) => (a >= b) == (a.amount >= b.amount)
    }
    "Money ==" verifies { 
        (a: Money, b: Money) => (a == b) == (a.amount == b.amount)
    }
    "Money !=" verifies { 
        (a: Money, b: Money) => (a != b) == (a.amount != b.amount)
    }
    
    for (i <- -4 to 4; delta = i * 0.00001) {
        "Two Moneys equal if amounts are within 0.0001 (" + delta + 
                ") of each other" verifies {
            (a: Money) => (a + Money(delta)) == a
        }
    }
    "Money equals is true if two amounts are within 0.0001 " + 
            "(-0.000049) of each other" verifies {
        (a: Money) => (a + Money(-0.000049)) == a
    }
    "Money equals is true if two amounts are within 0.0001 " + 
            "(0.000049) of each other" verifies {
        (a: Money) => (a + Money(0.000049)) == a
    }
    for (delta <- List(-0.01, -0.001, -0.0001, -0.000051, 
                        0.000051, 0.0001, 0.001, 0.01)) {
        "Money equals is false if two amounts are > 0.0001 (" + delta +
                ") of each other" verifies {
            (a: Money) => (a + Money(delta)) != a && (a - Money(delta)) != a
        }
    }
}
