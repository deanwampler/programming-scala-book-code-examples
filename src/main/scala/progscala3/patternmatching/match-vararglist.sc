// src/main/scala/progscala3/patternmatching/match-vararglist.sc

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

val results = wheres map {
  case WhereIn(col, val1, vals @ _*) =>                            // <5>
    val valStr = (val1 +: vals).mkString(", ")
    s"WHERE $col IN ($valStr)"
  case WhereOp(col, op, value) => s"WHERE $col $op $value"
  case x => s"ERROR: Unknown expression: $x"
}
assert(results == Seq(
  "WHERE state IN (IL, CA, VA)",
  "WHERE state = IL",
  "WHERE name = Buck Trends",
  "WHERE age > 29"))
