// src/main/scala/progscala3/basicoop/DollarsPercentagesTypes.scala
package progscala3.basicoop

object Accounting:
  type Dollars = Double                             // <2>
  type Percentage = Double                          // <3>

import Accounting._
def net(gross: Dollars, taxes: Percentage): Dollars = gross * (1.0 - taxes)

val gross: Dollars = 10000.0
val taxes: Percentage = 0.1
val net1 = net(gross, taxes)
val net2 = net(taxes, gross)
