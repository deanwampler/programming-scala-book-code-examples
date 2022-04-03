// src/test/scala/progscala3/fp/datastructs/FoldRegexPatternsSuite.scala
package progscala3.fp.datastructs

import munit.*

class FoldRegexPatternsSuite extends FunSuite:
  test("Regex pattern matching used in a foldLeft") {
    val ignoreRegex = """^\s*(#.*|\s*)$""".r                             // <1>
    val kvRegex = """^\s*([^=]+)\s*=\s*([^#]+)\s*.*$""".r                // <2>

    val properties = """
      |# Book properties
      |
      |book.name = Programming Scala, Third Edition # A comment
      |book.authors = Dean Wampler
      | an unexpected line
      |book.publisher = O'Reilly
      |book.publication-year = 2021
      |""".stripMargin                                                   // <3>

    // Type aliases so the rest of the code is easier to understand
    type Error = (Int,String)
    type KV = (String,String)

    // Parse each line, skipping expected
    val actual =
      properties.split("\n").
      zipWithIndex.
      foldLeft(Vector.empty[Either[Error,KV]]) { case (vect, (line, n)) =>
        if ignoreRegex.matches(line) then vect
        else line match
          case kvRegex(key, value) => vect :+ Right(key.trim -> value.trim)
          case _ => vect :+ Left(n+1, line.trim)
      }
    val expected = Vector(
      Right("book.name" -> "Programming Scala, Third Edition"),
      Right("book.authors" -> "Dean Wampler"),
      Left(6 -> "an unexpected line"),
      Right("book.publisher" -> "O'Reilly"),
      Right("book.publication-year" -> "2021"))
    assert(actual == expected, "Actual:\n$actual\nExpected:\n$expected")
  }
