// src/main/scala/progscala3/objectsystem/ui/button.sc
import progscala3.objectsystem.ui.Button

val b = new Button("Submit")
// Result: b: oop.ui.Button = (button: label=Submit, (widget))

assert(b.draw() == "Drawing: (button: label=Submit, (widget))")
