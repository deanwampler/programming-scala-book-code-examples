// src/main/scala/Rounding/assigned-if.sc

val configFile = new java.io.File(".myapprc")

val configFilePath = if (configFile.exists()) {
  configFile.getAbsolutePath()
} else {
  configFile.createNewFile()
  configFile.getAbsolutePath()
}
