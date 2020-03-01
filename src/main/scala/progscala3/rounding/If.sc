// src/main/scala/progscala3/rounding/If.sc

import progscala3.rounding.WeekDay

WeekDay.values foreach { day =>
  if (WeekDay.isWorkingDay(day)) {
    println(s"$day is a working day")
  } else if (day == WeekDay.Sat) {
    println("It's Saturday")
  } else {
    println("It's Sunday")
  }
}
