// code-examples/Rounding/assigned-if-script.scala

val configFile = new java.io.File(".myapprc")

val configFilePath = if (configFile.exists()) {
  configFile.getAbsolutePath()
} else {
  configFile.createNewFile()
  configFile.getAbsolutePath()
}