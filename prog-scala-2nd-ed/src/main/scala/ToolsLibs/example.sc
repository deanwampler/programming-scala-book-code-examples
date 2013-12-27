// src/main/scala/ToolsLibs/example.sc

case class Message(name: String)

def printMessage(msg: Message) = {
  println(msg)
}

printMessage(new Message(
    "Must compile this script with scalac -Xscript <name>!"))
