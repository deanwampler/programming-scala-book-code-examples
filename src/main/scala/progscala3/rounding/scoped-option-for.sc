// src/main/scala/progscala3/patternmatching/scoped-option-for.sc

import progscala3.rounding.WeekDay
import progscala3.rounding.WeekDay._

val days = Seq(
  Some(Mon), None, Some(Tue), Some(Wed), None, 
  Some(Thu), Some(Fri), Some(Sat), Some(Sun), None)

println("first pass:")
for {
  dayOpt <- days
  day <- dayOpt
  up   = WeekDay.upper(day)
} println(up)

println("second, more concise pass:")
for {
  Some(day) <- days
  up   = WeekDay.upper(day)
} println(up)
