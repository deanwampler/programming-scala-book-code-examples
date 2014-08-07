// src/test/scala/progscala2/typesystem/variances/java-option-spec.scala

package progscala2.typesystem.variances
 
import progscala2.introscala.shapes._
import progscala2.typesystem.variances.{Option => JOption, None => JNone, Some => JSome, _}
import scala.language.existentials
import org.scalatest.{ FunSpec, ShouldMatchers } 

class JavaOptionSpec extends FunSpec with ShouldMatchers { 
  def toD(d: Double) = new java.lang.Double(d)

  describe ("Java Option") { 
    val shapeNames = List("Rectangle", "Circle", "Triangle");
    var shapeOptions = List(
      OptionExample.makeShape(shapeNames(0), Point(0.0,0.0), toD(2.0), toD(5.0)),
      OptionExample.makeShape(shapeNames(1), Point(0.0,0.0), toD(2.0)),
      OptionExample.makeShape(shapeNames(2), Point(0.0,0.0), Point(1.0,0.0), Point(1.0,1.0)),
      new JNone[Shape]())

    it ("be an Java Some wrapping a Shape or be a Java None") {

      shapeOptions(0) match {
        case s: JSome[_] => s.get.isInstanceOf[Rectangle] shouldEqual true
        case x => fail(x.toString)
      }
      shapeOptions(1) match {
        case s: JSome[_] => s.get.isInstanceOf[Circle] shouldEqual true
        case x => fail(x.toString)
      }
      shapeOptions(2) match {
        case s: JSome[_] => s.get.isInstanceOf[Triangle] shouldEqual true
        case x => fail(x.toString)
      }
      shapeOptions(3) match {
        case s: JNone[_] => // pass
        case x => fail(x.toString)
      }
    }
  }
}
