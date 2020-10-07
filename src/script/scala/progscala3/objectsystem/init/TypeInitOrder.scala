// src/script/scala/progscala3/objectsystem/init/TypeInitOrder.scala

trait TT1:
  type TA
  type TB = Seq[TA]                                                     // <1>
  val seed: TA
  val seq: TB = Seq.fill(5)(seed)                                       // <2>

class TT2 extends TT1:
  type TA = Int                                                         // <3>
  val seed: TA = 5

val obj = new TT2
println(s"obj: seq = ${obj.seq}")
