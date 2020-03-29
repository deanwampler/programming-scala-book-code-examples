// src/script/scala/progscala3/rounding/If2.scala

import progscala3.rounding.WeekDay

val days = WeekDay.values map { day =>
  if WeekDay.isWorkingDay(day) then day.toString
  else if day == WeekDay.Sat then "Saturday"
  else "Sunday"
}

assert(days == Set("Fri", "Mon", "Saturday", "Sunday", "Thu", "Tue", "Wed"))