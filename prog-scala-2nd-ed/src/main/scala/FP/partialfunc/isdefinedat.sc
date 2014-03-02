// src/main/scala/FP/partialfunc/isdefinedat.sc

val pantsTest: PartialFunction[String, String] = {
  case "pants" => "yes, we have pants!"
}

println(pantsTest.isDefinedAt("pants"))
println(pantsTest.isDefinedAt("skort"))
