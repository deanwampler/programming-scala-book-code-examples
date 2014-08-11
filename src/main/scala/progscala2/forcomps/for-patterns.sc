// src/main/scala/progscala2/forcomps/for-patterns.sc

val ignoreRegex = """^\s*(#.*|\s*)$""".r                             // <1>
val kvRegex = """^\s*([^=]+)\s*=\s*([^#]+)\s*.*$""".r                // <2>

val properties = """
  |# Book properties
  |
  |book.name = Programming Scala, Second Edition # A comment 
  |book.authors = Dean Wampler and Alex Payne
  |book.publisher = O'Reilly
  |book.publication-year = 2014
  |""".stripMargin                                                   // <3>

val kvPairs = for {
  prop <- properties.split("\n")                                     // <4>
  if ignoreRegex.findFirstIn(prop) == None                           // <5>
  kvRegex(key, value) = prop                                         // <6>
} yield (key.trim, value.trim)                                       // <7>
// Returns: kvPairs: Array[(String, String)] = Array( 
//   (book.name,Programming Scala, Second Edition), 
//   (book.authors,Dean Wampler and Alex Payne), 
//   (book.publisher,O'Reilly), 
//   (book.publication-year,2014))
