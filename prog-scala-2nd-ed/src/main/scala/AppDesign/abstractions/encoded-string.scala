// src/main/scala/AppDesign/abstractions/encoded-string.scala

package appdesign.abstractions.encodedstring {

  trait EncodedString {
    protected[encodedstring] val string: String
    val separator: EncodedString.Separator.Delimiter

    override def toString = string

    def toTokens = string.split(separator.toString).toList
  }
  
  object EncodedString {
    object Separator extends Enumeration {
      type Delimiter = Value
      val COMMA = Value(",")
      val TAB   = Value("\t")
    }
            
    def apply(s: String, sep: Separator.Delimiter) = sep match {
      case Separator.COMMA => impl.CSV(s)
      case Separator.TAB   => impl.TSV(s)
    }

    def unapply(es: EncodedString) = Some(Pair(es.string, es.separator))
  }
  
  package impl {
    private[encodedstring] case class CSV(override val string: String)
        extends EncodedString {
      override val separator = EncodedString.Separator.COMMA
    }

    private[encodedstring] case class TSV(override val string: String)
        extends EncodedString {
      override val separator = EncodedString.Separator.TAB
    }
  }
}
