// src/main/scala/progscala3/patternmatching/scoped-option-for.sc

val dogBreeds = Seq(Some("Doberman"), None, Some("Yorkshire Terrier"), 
                    Some("Dachshund"), None, Some("Scottish Terrier"),
                    None, Some("Great Dane"), Some("Portuguese Water Dog"))

val db = for {
  Some(breed) <- dogBreeds
  upcasedBreed = breed.toUpperCase()
} yield upcasedBreed
assert(db == Seq(
  "DOBERMAN",
  "YORKSHIRE TERRIER",
  "DACHSHUND",
  "SCOTTISH TERRIER",
  "GREAT DANE",
  "PORTUGUESE WATER DOG"))
