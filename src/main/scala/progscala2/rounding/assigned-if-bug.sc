// src/main/scala/progscala2/rounding/assigned-if-bug.sc

val configFile = new java.io.File("somefile.txt")

val configFilePath = if (configFile.exists()) {
  configFile.getAbsolutePath()
}
