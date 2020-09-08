// src/script/scala/progscala3/typesystem/selectable/SelectableExample.scala

trait Shape extends reflect.Selectable:
	def area: Double

val square = new Shape:
	def area: Double = side*side
	def side = 10.0
	val name = "Square!"

assert(square.area == 100.0)
assert(square.side == 10.0)
assert(square.name == "Square!")
