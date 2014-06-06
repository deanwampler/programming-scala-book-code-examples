// src/main/scala/ToolsLibs/scanner.sc

import java.util.Scanner
import java.util.regex.MatchResult

val input = "1 fish 2 fish red fish blue fish"
val s = new Scanner(input)
s.findInLine("""(\d+) fish (\d+) fish (\w+) fish (\w+)""")
val result: MatchResult = s.`match`
for (i <- 1 to result.groupCount) print(result.group(i) + " ")
println()
s.close()
 