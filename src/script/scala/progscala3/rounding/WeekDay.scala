// tag::weekday[]
// src/script/scala/progscala3/rounding/WeekDay.scala
enum WeekDaySimple:                                             // <1>
  case Sun, Mon, Tue, Wed, Thu, Fri, Sat

enum WeekDay(val fullName: String):                             // <2>
  case Sun extends WeekDay("Sunday")                            // <3>
  case Mon extends WeekDay("Monday")
  case Tue extends WeekDay("Tuesday")
  case Wed extends WeekDay("Wednesday")
  case Thu extends WeekDay("Thursday")
  case Fri extends WeekDay("Friday")
  case Sat extends WeekDay("Saturday")

  def isWorkingDay: Boolean = ! (this == Sat || this == Sun)    // <4>

import WeekDay.*

val sorted = WeekDay.values.sortBy(_.ordinal).toSeq             // <5>
assert(sorted == List(Sun, Mon, Tue, Wed, Thu, Fri, Sat))

assert(Sun.fullName == "Sunday")
assert(Sun.ordinal == 0)                                        // <6>
assert(Sun.isWorkingDay == false)
assert(WeekDay.valueOf("Sun") == WeekDay.Sun)                   // <7>
// end::weekday[]

assert(Mon.fullName == "Monday")
assert(Mon.ordinal == 1)
assert(Mon.isWorkingDay == true)
assert(WeekDay.valueOf("Mon") == Mon)

assert(Tue.fullName == "Tuesday")
assert(Tue.ordinal == 2)
assert(Tue.isWorkingDay == true)
assert(WeekDay.valueOf("Tue") == Tue)

assert(Wed.fullName == "Wednesday")
assert(Wed.ordinal == 3)
assert(Wed.isWorkingDay == true)
assert(WeekDay.valueOf("Wed") == Wed)

assert(Thu.fullName == "Thursday")
assert(Thu.ordinal == 4)
assert(Thu.isWorkingDay == true)
assert(WeekDay.valueOf("Thu") == Thu)

assert(Fri.fullName == "Friday")
assert(Fri.ordinal == 5)
assert(Fri.isWorkingDay == true)
assert(WeekDay.valueOf("Fri") == Fri)

assert(Sat.fullName == "Saturday")
assert(Sat.ordinal == 6)
assert(Sat.isWorkingDay == false)
assert(WeekDay.valueOf("Sat") == Sat)
