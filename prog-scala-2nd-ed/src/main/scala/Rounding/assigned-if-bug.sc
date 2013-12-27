// src/main/scala/Rounding/assigned-if-bug.sc

val configFile = new java.io.File("~/.myapprc")

val configFilePath = if (configFile.exists()) {
  configFile.getAbsolutePath()
}
