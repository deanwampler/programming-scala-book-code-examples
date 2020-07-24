// src/script/scala/progscala3/rounding/DaysEnumeration.scala

enum WeekDay(val name: String) {
  case Mon extends WeekDay("Monday")
  case Tue extends WeekDay("Tuesday")
  case Wed extends WeekDay("Wednesday")
  case Thu extends WeekDay("Thursday")
  case Fri extends WeekDay("Friday")
  case Sat extends WeekDay("Saturday")
  case Sun extends WeekDay("Sunday")

  def isWorkingDay: Boolean = ! (this == Sat || this == Sun)
}

val tue = WeekDay.Tue
assert(tue.ordinal == 1)
assert(WeekDay.valueOf("Tue") == WeekDay.Tue)
// Values order is not as declared or alphabetical:
val sorted = WeekDay.values.sortBy(_.name).toSeq
assert(sorted == List(
  WeekDay.Fri, WeekDay.Mon, WeekDay.Sat, WeekDay.Sun,
  WeekDay.Thu, WeekDay.Tue, WeekDay.Wed))
assert(sorted.map(_.isWorkingDay) == List(
  true, true, false, false, true, true, true))
