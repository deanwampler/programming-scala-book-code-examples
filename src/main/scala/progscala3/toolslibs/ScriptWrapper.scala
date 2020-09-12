// src/main/scala/progscala3/toolslibs/ScriptWrapper.scala

object ScriptWrapper:
  def main(params: Array[String]): Unit =
    val result = new AnyRef:
      // Your script code is inserted here.
      print("End of script")
    println(s"result: $result")
