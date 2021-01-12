// src/script/scala-2/progscala3/rounding/ScopedFor.scala

import progscala3.rounding.WeekDay

val days = for {
  day <- WeekDay.values
  if day.isWorkingDay
  fn = day.fullName
} yield fn

assert(days.toSeq ==
  Seq("Friday", "Monday", "Tuesday", "Wednesday", "Thursday"))
