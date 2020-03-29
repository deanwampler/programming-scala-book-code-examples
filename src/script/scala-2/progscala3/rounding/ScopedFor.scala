// src/script/scala-2/progscala3/rounding/ScopedFor.scala

import progscala3.rounding.WeekDay

val days = for {
  day <- WeekDay.values
  if WeekDay.isWorkingDay(day)
  up = WeekDay.upper(day)
} yield up

assert(days == Set("MON", "TUE", "WED", "THU", "FRI"))
