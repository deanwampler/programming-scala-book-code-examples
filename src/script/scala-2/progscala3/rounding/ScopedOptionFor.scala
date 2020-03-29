// src/script/scala-2/progscala3/patternmatching/ScopedOptionFor.scala

import progscala3.rounding.WeekDay
import progscala3.rounding.WeekDay._

val dayOptions = Seq(
  Some(Mon), None, Some(Tue), Some(Wed), None,
  Some(Thu), Some(Fri), Some(Sat), Some(Sun), None)

println("first pass:")
val goodDays1 = for {
  dayOpt <- dayOptions
  day <- dayOpt
  up   = WeekDay.upper(day)
} yield up
assert(goodDays1 == Seq("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"))

println("second, more concise pass:")
val goodDays2 = for {
  Some(day) <- dayOptions
  up   = WeekDay.upper(day)
} yield up
assert(goodDays1 == Seq("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"))
