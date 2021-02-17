// src/script/scala-2/progscala3/rounding/WeekDay.scala

/**
 * A Scala 2 enumeration definition. See the corresponding Scala 3 version
 * in src/script/scala/progscala3/rounding.
 */
object WeekDay extends Enumeration {
  type WeekDay = Value
  val Sun = Value("Sunday")
  val Mon = Value("Monday")
  val Tue = Value("Tuesday")
  val Wed = Value("Wednesday")
  val Thu = Value("Thursday")
  val Fri = Value("Friday")
  val Sat = Value("Saturday")

  def isWorkingDay(wd: WeekDay): Boolean = ! (wd == Sat || wd == Sun)
}
import WeekDay.*

assert(Sun.toString == "Sunday")
assert(Sun.id == 0)
assert(WeekDay(0) == Sun)
assert(WeekDay.isWorkingDay(Sun) == false)

assert(Mon.toString == "Monday")
assert(Mon.id == 1)
assert(WeekDay(1) == Mon)
assert(WeekDay.isWorkingDay(Mon) == true)

assert(Tue.toString == "Tuesday")
assert(Tue.id == 2)
assert(WeekDay(2) == Tue)
assert(WeekDay.isWorkingDay(Tue) == true)

assert(Wed.toString == "Wednesday")
assert(Wed.id == 3)
assert(WeekDay(3) == Wed)
assert(WeekDay.isWorkingDay(Wed) == true)

assert(Thu.toString == "Thursday")
assert(Thu.id == 4)
assert(WeekDay(4) == Thu)
assert(WeekDay.isWorkingDay(Thu) == true)

assert(Fri.toString == "Friday")
assert(Fri.id == 5)
assert(WeekDay(5) == Fri)
assert(WeekDay.isWorkingDay(Fri) == true)

assert(Sat.toString == "Saturday")
assert(Sat.id == 6)
assert(WeekDay(6) == Sat)
assert(WeekDay.isWorkingDay(Sat) == false)

val sorted = WeekDay.values.toSeq.sortBy(_.id)
assert(sorted == List( Sun, Mon, Tue, Wed, Thu, Fri, Sat))
