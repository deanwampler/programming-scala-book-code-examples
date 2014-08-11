// src/main/scala/progscala2/objectsystem/CommandArgs.scala
package progscala2.objectsystem

object CommandArgs {

  val help = """
  |usage: java ... objectsystem.CommandArgs arguments
  |where the allowed arguments are:
  |  -h | --help                 Show help
  |  -i | --in | --input path    Path for input
  |  -o | --on | --output path   Path for input
  |""".stripMargin

  def quit(message: String, status: Int): Nothing = {                // <1>
    if (message.length > 0) println(message)
    println(help)
    sys.exit(status)
  }

  case class Args(inputPath: String, outputPath: String)             // <2>

  def parseArgs(args: Array[String]): Args = {
    def pa(args2: List[String], result: Args): Args = args2 match {  // <3>
      case Nil => result                                             // <4>
      case ("-h" | "--help") :: Nil => quit("", 0)                   // <5>
      case ("-i" | "--in" | "--input") :: path :: tail =>            // <6>
        pa(tail, result copy (inputPath = path))                     // <7>
      case ("-o" | "--out" | "--output") :: path :: tail =>          // <8>
        pa(tail, result copy (outputPath = path))
      case _ => quit(s"Unrecognized argument ${args2.head}", 1)      // <9>
    }
    val argz = pa(args.toList, Args("", ""))                         // <10>
    if (argz.inputPath == "" || argz.outputPath == "")               // <11>
      quit("Must specify input and output paths.", 1)
    argz
  }

  def main(args: Array[String]) = {
    val argz = parseArgs(args)
    println(argz)
  }
}
