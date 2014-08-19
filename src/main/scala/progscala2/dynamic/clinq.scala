// src/main/scala/progscala2/dynamic/CLINQ.scala
package progscala2.dynamic
import scala.language.dynamics                                       // <1>

case class CLINQ[T](records: Seq[Map[String,T]]) extends Dynamic {

  def selectDynamic(name: String): CLINQ[T] =                        // <2>
    if (name == "all" || records.length == 0) this                   // <3>
    else {
      val fields = name.split("_and_")                               // <4>
      val seed = Seq.empty[Map[String,T]]
      val newRecords = (records foldLeft seed) {
        (results, record) =>
          val projection = record filter {                           // <5>
            case (key, value) => fields contains key
          }
          // Drop records with no projection.
          if (projection.size > 0) results :+ projection
          else results
      }
      CLINQ(newRecords)                                              // <6>
    }

  def applyDynamic(name: String)(field: String): Where = name match {
    case "where" => new Where(field)                                 // <7>
    case _ => throw CLINQ.BadOperation(field, """Expected "where".""")
  }

  protected class Where(field: String) extends Dynamic {             // <8>
    def filter(value: T)(op: (T,T) => Boolean): CLINQ[T] = {         // <9>
      val newRecords = records filter {
        _ exists {
          case (k, v) => field == k && op(value, v)
        }
      }
      CLINQ(newRecords)
    }

    def applyDynamic(op: String)(value: T): CLINQ[T] = op match {
      case "EQ" => filter(value)(_ == _)                             // <10>
      case "NE" => filter(value)(_ != _)                             // <11>
      case _ => throw CLINQ.BadOperation(field, """Expected "EQ" or "NE".""")
    }
  }

  override def toString: String = records mkString "\n"              // <12>
}

object CLINQ {                                                       // <13>
  case class BadOperation(name: String, msg: String) extends RuntimeException(
    s"Unrecognized operation $name. $msg")
}
