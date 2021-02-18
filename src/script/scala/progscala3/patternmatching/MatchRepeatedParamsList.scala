// src/script/scala/progscala3/patternmatching/MatchRepeatedParamsList.scala
// This example is not in the book, but it shows a more extensive example
// of repeated parameters matching.

// Operators for WHERE clauses
enum Op(val symbol: String) {                                        // <1>
  case EQ   extends Op("=")
  case NE   extends Op("!=")
  case LTGT extends Op("<>")
  case LT   extends Op("<")
  case LE   extends Op("<=")
  case GT   extends Op(">")
  case GE   extends Op(">=")
}
import Op.*

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

val results = wheres.map {
  case WhereIn(col, val1, vals*) =>                                  // <5>
    val valStr = (val1 +: vals).mkString(", ")
    s"WHERE $col IN ($valStr)"
  case WhereOp(col, op, value) => s"WHERE $col ${op.symbol} $value"
  case x => s"ERROR: Unknown expression: $x"
}
assert(results == Seq(
  "WHERE state IN (IL, CA, VA)",
  "WHERE state = IL",
  "WHERE name = Buck Trends",
  "WHERE age > 29"))
