// src/script/scala/progscala3/rounding/BasicFor.scala

import progscala3.rounding.WeekDay

for
  day <- WeekDay.values
do println(day)

for day <- WeekDay.values
do println(day)
