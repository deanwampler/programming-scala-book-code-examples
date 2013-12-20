// code-examples/ToolsLibs/example-script.scala

case class Message(name: String)

def printMessage(msg: Message) = {
  println(msg)
}

printMessage(new Message(
    "Must compile this script with scalac -Xscript <name>!"))
