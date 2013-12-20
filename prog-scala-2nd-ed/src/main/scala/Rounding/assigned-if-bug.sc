// code-examples/Rounding/assigned-if-bug-script.scala

val configFile = new java.io.File("~/.myapprc")

val configFilePath = if (configFile.exists()) {
  configFile.getAbsolutePath()
}