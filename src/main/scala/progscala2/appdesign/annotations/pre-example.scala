// src/main/scala/progscala2/appdesign/annotations/pre-example.scala

package progscala2.appdesign.annotations
import org.contract4j5.contract._

class Person(
  @Pre( "name != null && name.length() > 0" ) 
  val name: String,
	// For Scala 2.7.X, you used the following syntax.
	// @Pre{ val value = "age > 0", val message = "You're too young!" }
  @Pre( value = "age > 0", message = "You're too young!" )
  val age: Int,
  @Pre( "ssn != null" )
  val ssn: SSN)
    
class SSN(
	// The following syntax was also supported in 2.7.X, but was dropped as of Scala 2.8.
  // @Pre( "valid(ssn)" ) { val message = "Format must be NNN-NN-NNNN." }
  @Pre( value = "valid(ssn)", message = "Format must be NNN-NN-NNNN." )
  val ssn: String) {

  private def valid(value: String) = 
    value.matches("""^\s*\d{3}-\d{2}-\d{4}\s*$""")
}
