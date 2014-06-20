// src/main/scala/PatternMatching/scoped-option-for.sc

val dogBreeds = List(Some("Doberman"), None, Some("Yorkshire Terrier"), 
                     Some("Dachshund"), None, Some("Scottish Terrier"),
                     None, Some("Great Dane"), Some("Portuguese Water Dog"))

println("second pass:")
for {
  Some(breed) <- dogBreeds
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)
