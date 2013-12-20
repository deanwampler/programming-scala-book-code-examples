// code-examples/TypeSystem/variances/java-option-spec.scala
package variances
import org.specs2.mutable._ 
import shapes._  // From "Introducing Scala" chapter
import variances.{Option => JOption, None => JNone, Some => JSome, _}

object JavaOptionSpec extends Specification { 
  "Java Option" should { 
    val shapeNames = List("Rectangle", "Circle", "Triangle");
    var shapeOptions = List(
      OptionExample.makeShape(shapeNames(0), Point(0.,0.), (2.).asInstanceOf[Object], (5.).asInstanceOf[Object]),
      OptionExample.makeShape(shapeNames(1), Point(0.,0.), (2.).asInstanceOf[Object]),
      OptionExample.makeShape(shapeNames(2)))

    "be an Java Some wrapping a Shape or be a Java None" in {

      shapeOptions(0) match {
        case s: JSome[_] => s.get.isInstanceOf[Rectangle] must beTrue
        case x => fail(x.toString)
      }
      shapeOptions(1) match {
        case s: JSome[_] => s.get.isInstanceOf[Circle] must beTrue
        case x => fail(x.toString)
      }
      shapeOptions(2) match {
        case s: JNone[_] => 
        case x => fail(x.toString)
      }
    }
  }
}
