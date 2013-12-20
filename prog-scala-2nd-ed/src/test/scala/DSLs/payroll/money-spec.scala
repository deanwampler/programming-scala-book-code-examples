// code-examples/DSLs/payroll/money-spec.scala
package payroll

import org.specs2.mutable._ 
import org.scalacheck._
import org.scalacheck.Prop._

object MoneySpec extends Specification("Money") 
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
    
    for (i <- -4 to 4; delta = i * .00001) {
        "Two Moneys equal if amounts are within .0001 (" + delta + 
                ") of each other" verifies {
            (a: Money) => (a + Money(delta)) == a
        }
    }
    "Money equals is true if two amounts are within .0001 " + 
            "(-.000049) of each other" verifies {
        (a: Money) => (a + Money(-.000049)) == a
    }
    "Money equals is true if two amounts are within .0001 " + 
            "(.000049) of each other" verifies {
        (a: Money) => (a + Money(.000049)) == a
    }
    for (delta <- List(-.01, -.001, -.0001, -.000051, 
                        .000051, .0001, .001, .01)) {
        "Money equals is false if two amounts are > .0001 (" + delta +
                ") of each other" verifies {
            (a: Money) => (a + Money(delta)) != a && (a - Money(delta)) != a
        }
    }
}
