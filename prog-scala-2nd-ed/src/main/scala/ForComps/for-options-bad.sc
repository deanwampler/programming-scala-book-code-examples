// src/main/scala/ForComps/for-options-bad.sc

def doThreeSteps(
    step1: Int => Option[Int], 
    step2: Int => Option[Int], 
    step3: Int => Option[Int]): Option[Int] = {
  val result1 = step1(0) match {
    case None => return None
    case Some(result) => result
  }
  val result2 = step2(result1) match {
    case None => return None
    case Some(result) => result
  }
  step3(result2)
}

// Returns: Option[Int] = Some(40)
doThreeSteps(
  i1 => Some(i1 + 5),
  i2 => Some(i2 + 10),
  i3 => Some(i3 + 25))

// Returns: Option[Int] = None
doThreeSteps(
  i1 => Some(i1 + 5),
  i2 => None,   // EPIC FAIL!
  i3 => Some(i3 + 25))
