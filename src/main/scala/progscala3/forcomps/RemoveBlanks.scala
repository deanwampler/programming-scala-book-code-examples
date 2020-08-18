// src/main/scala/progscala3/forcomps/RemoveBlanks.scala
package progscala3.forcomps

object RemoveBlanks:
  def apply(path: String, compress: Boolean, numbers: Boolean): Seq[String] =
    for                                                              // <1>
      (line, i) <- scala.io.Source.fromFile(path).getLines.toSeq.zipWithIndex
      if line.matches("""^\s*$""") == false                          // <2>
      line2 = if compress then line.trim.replaceAll("\\s+", " ")     // <3>
              else line
      numLine = if numbers then "%4d: %s".format(i, line2)           // <4>
              else line2
    yield numLine

  def main(args: Array[String]): Unit =                              // <5>

    val Params(compress, numbers, paths) = parseArgs(args.toSeq, Params())
    for                                                              // <6>
      path <- paths
      seq = s"\n== File: $path\n" +: RemoveBlanks(path, compress, numbers)
      line <- seq
    do println(line)

  protected val helpMessage = """
    |usage: RemoveBlanks [-h|--help] [-c|--compress] [-n|--numbers] file ...
    |where:
    | -h | --help     Print this message and quit
    | -c | --compress Compress whitespace
    | -n | --numbers  Print line numbers
    | file ...        One or more files to print without blanks
    |""".stripMargin

  protected case class Params(                                       // <7>
    compress: Boolean = false,
    numbers: Boolean = false,
    paths: Vector[String] = Vector.empty)

  protected def help(messages: Seq[String], exitCode: Int) =
    messages.foreach(println)
    println(helpMessage)
    sys.exit(exitCode)

  protected def parseArgs(args2: Seq[String], params: Params): Params =
    args2 match
      case ("-h" | "--help") +: tail =>
        println(helpMessage)
        sys.exit(0)
      case ("-c" | "--compress") +: tail =>
        parseArgs(tail, params.copy(compress = true))
      case ("-n" | "--number") +: tail =>
        parseArgs(tail, params.copy(numbers = true))
      case flag +: tail if flag.startsWith("-") =>
        println(s"ERROR: Unknown option $flag")
        println(helpMessage)
        sys.exit(1)
      case path +: tail =>
        parseArgs(tail, params.copy(paths = params.paths :+ path))
      case Nil => params

