// src/script/scala/progscala3/objectsystem/ui/Button.scala
import progscala3.objectsystem.ui.Button

val b = new Button("Submit")
// Result: b: oop.ui.Button = (button: label=Submit, (widget))

assert(b.draw() == "Drawing: (button: label=Submit, (widget))")
