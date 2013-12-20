// code-examples/Rounding/enumeration-script.scala

object Breed extends Enumeration {
  val doberman = Value("Doberman Pinscher")
  val yorkie = Value("Yorkshire Terrier")
  val scottie = Value("Scottish Terrier")
  val dane = Value("Great Dane")
  val portie = Value("Portuguese Water Dog")
}

// print a list of breeds and their IDs
println("ID\tBreed")
for (breed <- Breed) println(breed.id + "\t" + breed)

// print a list of Terrier breeds
println("\nJust Terriers:")
Breed.filter(_.toString.endsWith("Terrier")).foreach(println)