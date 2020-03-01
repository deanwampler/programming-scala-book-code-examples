// src/script/scala/progscala3/rounding/DaysEnumeration.scala

import progscala3.rounding.WeekDay
import progscala3.rounding.WeekDay._

assert(WeekDay.values.toSeq.map(_.toString) == Seq(        // <1>
  "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"))

val result = WeekDay.values filter isWorkingDay map upper
assert(result == Set("MON", "TUE", "WED", "THU", "FRI"))

val seq = WeekDay.values.toSeq
val ids = seq.map(day => (day.id, day))
assert(ids == 
  Seq((0, Mon), (1, Tue), (2, Wed), (3, Thu), (4, Fri), (5, Sat), (6, Sun)))

import progscala3.rounding.WeekDay2                        // <2>

assert(WeekDay2.values.toSeq.map(_.toString) == Seq(       // <3>
  "Monday", "Tuesday", "Wednesday", "Thursday", 
  "Friday", "Saturday", "Sunday"))
