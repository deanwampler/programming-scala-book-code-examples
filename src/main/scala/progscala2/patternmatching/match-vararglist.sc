// src/main/scala/progscala2/patternmatching/match-vararglist.sc

// Operators for WHERE clauses
object Op extends Enumeration {                                      // <1>
  type Op = Value

  val EQ   = Value("=")
  val NE   = Value("!=")
  val LTGT = Value("<>")
  val LT   = Value("<")
  val LE   = Value("<=")
  val GT   = Value(">")
  val GE   = Value(">=")
}
import Op._

// Represent a SQL "WHERE x op value" clause, where +op+ is a 
// comparison operator: =, !=, <>, <, <=, >, or >=.
case class WhereOp[T](columnName: String, op: Op, value: T)          // <2>

// Represent a SQL "WHERE x IN (a, b, c, ...)" clause.
case class WhereIn[T](columnName: String, val1: T, vals: T*)         // <3>

val wheres = Seq(                                                    // <4>
  WhereIn("state", "IL", "CA", "VA"),
  WhereOp("state", EQ, "IL"),
  WhereOp("name", EQ, "Buck Trends"),
  WhereOp("age", GT, 29))

for (where <- wheres) {
  where match {
    case WhereIn(col, val1, vals @ _*) =>                            // <5>
      val valStr = (val1 +: vals).mkString(", ")
      println (s"WHERE $col IN ($valStr)")
    case WhereOp(col, op, value) => println (s"WHERE $col $op $value")
    case _ => println (s"ERROR: Unknown expression: $where")
  }
}
