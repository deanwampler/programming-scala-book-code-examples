// src/script/scala-2/progscala3/rounding/DoubleGuardFor.scala

import progscala3.rounding.WeekDay

for {
  day <- WeekDay.values
  if WeekDay.isWorkingDay(day)
} println(day)
