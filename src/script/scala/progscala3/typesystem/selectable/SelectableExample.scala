// src/script/scala/progscala3/typesystem/selectable/SelectableExample.scala

trait Shape extends reflect.Selectable:                         // <1>
  def area: Double

val square = new Shape:
  val name = "Square"                                           // <2>
  def side = 10.0
  def area: Double = side*side

assert(square.name == "Square")
assert(square.area == 100.0)
assert(square.side == 10.0)
