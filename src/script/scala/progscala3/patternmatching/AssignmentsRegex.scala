// src/script/scala/progscala3/patternmatching/AssignmentsRegex.scala

val c = """\*|[\w, ]+"""  // cols
val t = """\w+"""         // table
val o = """.*"""          // other substrings
val selectRE =
  s"""SELECT\\s*(DISTINCT)?\\s+($c)\\s*FROM\\s+($t)\\s*($o)?;""".r

val selectRE (distinct1, cols1, table1, otherClauses1) =
  "SELECT DISTINCT * FROM atable;": @unchecked
assert(distinct1 == "DISTINCT")
assert(cols1 == "*")
assert(table1 == "atable")
assert(otherClauses1 == "")

val selectRE(distinct2, cols2, table2, otherClauses2) =
  "SELECT col1, col2 FROM atable;": @unchecked
assert(distinct2 == null)
assert(cols2 == "col1, col2 ")
assert(table2 == "atable")
assert(otherClauses2 == "")

val selectRE(distinct3, cols3, table3, otherClauses3) =
  "SELECT DISTINCT col1, col2 FROM atable;": @unchecked
assert(distinct3 == "DISTINCT")
assert(cols3 == "col1, col2 ")
assert(table3 == "atable")
assert(otherClauses3 == "")

val selectRE(distinct4, cols4, table4, otherClauses4) =
  "SELECT DISTINCT col1, col2 FROM atable WHERE col1 = 'foo';": @unchecked
assert(distinct4 == "DISTINCT")
assert(cols4 == "col1, col2 ")
assert(table4 == "atable")
assert(otherClauses4 == "WHERE col1 = 'foo'")

val selectRE(distinct, cols, table, otherClauses) =
  "SELECT DISTINCT col1, col2 FROM atable WHERE col1 = 'foo';": @unchecked
