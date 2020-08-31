// src/main/scala/progscala3/rounding/DaysEnumeration.scala

package progscala3.rounding

enum WeekDay(val fullName: String) derives Eql:
  case Sun extends WeekDay("Sunday")
  case Mon extends WeekDay("Monday")
  case Tue extends WeekDay("Tuesday")
  case Wed extends WeekDay("Wednesday")
  case Thu extends WeekDay("Thursday")
  case Fri extends WeekDay("Friday")
  case Sat extends WeekDay("Saturday")

  def isWorkingDay: Boolean = ! (this == Sat || this == Sun)
