// src/script/scala/progscala3/toolslibs/Example.scala

case class Message(name: String)

def printMessage(msg: Message) = println(msg)

printMessage(new Message("This works fine with the REPL"))
