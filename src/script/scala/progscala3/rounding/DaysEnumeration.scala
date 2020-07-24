// src/script/scala/progscala3/rounding/DaysEnumeration.scala

enum WeekDay(val fullName: String) {                           // <1>
  case Mon extends WeekDay("Monday")                           // <2>
  case Tue extends WeekDay("Tuesday")
  case Wed extends WeekDay("Wednesday")
  case Thu extends WeekDay("Thursday")
  case Fri extends WeekDay("Friday")
  case Sat extends WeekDay("Saturday")
  case Sun extends WeekDay("Sunday")

  def isWorkingDay: Boolean = ! (this == Sat || this == Sun)   // <3>
}

assert(WeekDay.Mon.fullName == "Monday")
assert(WeekDay.Mon.ordinal == 0)                               // <4>
assert(WeekDay.Mon.isWorkingDay == true)
assert(WeekDay.valueOf("Mon") == WeekDay.Mon)                  // <5>

assert(WeekDay.Tue.fullName == "Tuesday")
assert(WeekDay.Tue.ordinal == 1)
assert(WeekDay.Tue.isWorkingDay == true)
assert(WeekDay.valueOf("Tue") == WeekDay.Tue)

assert(WeekDay.Wed.fullName == "Wednesday")
assert(WeekDay.Wed.ordinal == 2)
assert(WeekDay.Wed.isWorkingDay == true)
assert(WeekDay.valueOf("Wed") == WeekDay.Wed)

assert(WeekDay.Thu.fullName == "Thursday")
assert(WeekDay.Thu.ordinal == 3)
assert(WeekDay.Thu.isWorkingDay == true)
assert(WeekDay.valueOf("Thu") == WeekDay.Thu)

assert(WeekDay.Fri.fullName == "Friday")
assert(WeekDay.Fri.ordinal == 4)
assert(WeekDay.Fri.isWorkingDay == true)
assert(WeekDay.valueOf("Fri") == WeekDay.Fri)

assert(WeekDay.Sat.fullName == "Saturday")
assert(WeekDay.Sat.ordinal == 5)
assert(WeekDay.Sat.isWorkingDay == false)
assert(WeekDay.valueOf("Sat") == WeekDay.Sat)

assert(WeekDay.Sun.fullName == "Sunday")
assert(WeekDay.Sun.ordinal == 6)
assert(WeekDay.Sun.isWorkingDay == false)
assert(WeekDay.valueOf("Sun") == WeekDay.Sun)

val sorted = WeekDay.values.sortBy(_.fullName).toSeq           // <6>
assert(sorted == List(
  WeekDay.Fri, WeekDay.Mon, WeekDay.Sat, WeekDay.Sun,
  WeekDay.Thu, WeekDay.Tue, WeekDay.Wed))
