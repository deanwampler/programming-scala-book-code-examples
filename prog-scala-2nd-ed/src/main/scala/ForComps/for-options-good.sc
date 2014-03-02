// src/main/scala/ForComps/for-options-good.sc

def doThreeSteps(
    step1: Int => Option[Int], 
    step2: Int => Option[Int], 
    step3: Int => Option[Int]): Option[Int] = 
  step1(0) map step2 map step3

doThreeSteps(
  i1 => Some(i1 + 5),
  i2 => Some(i2 + 10),
  i3 => Some(i3 + 25))
// Returns: Option[Int] = Some(40)

doThreeSteps(
  i1 => Some(i1 + 5),
  i2 => None,   // EPIC FAIL!
  i3 => Some(i3 + 25))
// Returns: Option[Int] = None
