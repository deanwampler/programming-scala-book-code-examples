// src/main/scala/progscala2/toolslibs/example.sc

case class Message(name: String)

def printMessage(msg: Message) = println(msg)

printMessage(new Message("This works fine with the REPL"))
