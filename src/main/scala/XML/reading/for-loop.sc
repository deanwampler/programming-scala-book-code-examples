// src/main/scala/XML/reading/for-loop.sc

for (condiment <- (someXML \\ "condiment")) {
  if ((condiment \ "@expired").text == "true")
    println("the " + condiment.text + " has expired!")
}
