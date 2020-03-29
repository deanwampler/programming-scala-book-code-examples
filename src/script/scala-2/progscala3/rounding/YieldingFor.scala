// src/script/scala-2/progscala3/rounding/YieldingFor.scala

import progscala3.rounding.WeekDay

val days = for {
  day <- WeekDay.values
  if WeekDay.isWorkingDay(day)
} yield day

assert(days == Set(Mon, Tue, Wed, Thu, Fri))
