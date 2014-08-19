// src/main/scala/progscala2/objectsystem/ui/button.sc
import progscala2.objectsystem.ui.Button

val b = new Button("Submit")
// Result: b: oop.ui.Button = (button: label=Submit, (widget))

b.draw()
// Result: Drawing: (button: label=Submit, (widget))
