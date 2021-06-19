// src/main/scala/progscala3/contexts/SQLStringInterpolator.scala
package progscala3.contexts

/**
 * An implementation of a string interpolator that handles this subset of SQL:
 *   sql"SELECT columns FROM table;".
 * You can hard code comma-separated column values and the table or use variables
 * for them. See the corresponding test, SQLStringInterpolatorSuite.
 * This example is mentioned in the book, but not included there.
 */
object SimpleSQL:
  /**
   * A simple case class to hold the extract column names and the table name.
   */
  case class SQLQuery(columns: Seq[String] = Nil, table: String = "")

  // To understand what the "hacky" implementation of "sql" is doing, try adding
  // print statements to see what's in "values" and "sc.parts", and what each
  // stage of the calculation computes.
  extension (sc: StringContext)
    // The extension method defined is +sql+.
    def sql(values: String*): SQLQuery =
      // Two helper methods. One for converting a two-element tuples into
      // two-element sequences, used in a +flatMap+ step, and the other for
      // filtering out strings we don't want.
      def tup2Seq(t: (String,String)): Seq[String] = Seq(t._1, t._2)     // <3>
      def keep(s: String): Boolean = s.length > 0 && s != "SELECT" && s != "FROM"

      // It turns out that the length of the "sc.parts" passed to +sql+ will always
      // be one more than the length of the "values". If we use no variables,
      // "sc.parts" has one element, the whole string, and "values" is empty.
      // If we use a variable for the columns, then "sc.parts" will be
      // "Seq("SELECT ", "FROM tableName")" and "values" will contain a string for columns.
      // Using a variable for the table name instead will be similar, but "sc.parts"
      // will end with an empty string. You can add print statements to see this
      // in action.
      // This is why these two sequences are combined with "zipAll", so we can
      // get all the substrings back into the proper order. Using "zipAll" instead
      // of "zip" lets us handle the case that the second sequence is shorter than
      // the first; we use empty strings to "pad" the tuples created.
      val strs = sc.parts.toVector.zipAll(values, "", "").flatMap(tup2Seq)
      // Split the strings into tokens, using runs of whitespace, "," and ";"
      // as the delimiters, then trim whitespace off the strings and filter them.
      val tokens = strs.flatMap(_.split("[\\s,;]+")).map(_.trim).filter(keep)
      // At this point, the column names are all of "tokens" except the last value...
      val columns = tokens.take(tokens.size-1)
      // ... which is the table name.
      val table = tokens.last
      SQLQuery(columns, table)



