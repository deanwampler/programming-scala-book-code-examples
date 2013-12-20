// code-examples/AdvOOP/objects/widget.scala

package advoop.objects

abstract class Widget {
  def draw(): Unit
  override def toString() = "(widget)"
}

object Widget {
  val ButtonExtractorRE = """\(button: label=([^,]+),\s+\(Widget\)\)""".r
  val TextFieldExtractorRE = """\(textfield: text=([^,]+),\s+\(Widget\)\)""".r

  def apply(specification: String): Option[Widget] = specification match {
    case ButtonExtractorRE(label)   => new Some(new Button(label))
    case TextFieldExtractorRE(text) => new Some(new TextField(text))
    case _ => None
  }
}

