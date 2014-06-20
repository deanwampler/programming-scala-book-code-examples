// src/main/scala/Rounding/guard-for.sc

val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
for (breed <- dogBreeds
  if breed.contains("Terrier")
) println(breed)
