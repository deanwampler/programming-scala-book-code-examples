// code-examples/AppDesign/abstractions/encoded-string-script.scala

import appdesign.abstractions.encodedstring._
import appdesign.abstractions.encodedstring.EncodedString._

def p(s: EncodedString) = {
  println("EncodedString: " + s)
  s.toTokens foreach (x => println("token: " + x))
}

val csv = EncodedString("Scala,is,great!", Separator.COMMA)
val tsv = EncodedString("Scala\tis\tgreat!", Separator.TAB)

p(csv)
p(tsv)

println( "\nExtraction:" )
List(csv, "ProgrammingScala", tsv, 3.14159) foreach { 
  case EncodedString(str, delim) => 
    println( "EncodedString: \"" + str + "\", delimiter: \"" + delim + "\"" )
  case s: String => println( "String: " + s )
  case x => println( "Unknown Value: " + x )
}
