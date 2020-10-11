// src/main/scala/progscala3/objectsystem/CommandArgs.scala
package progscala3.objectsystem

object CommandArgs:

  val help = """
  |Usage: progscala3.objectsystem.CommandArgs arguments
  |Where the allowed arguments are:
  |  -h | --help                 Show help
  |  -i | --in | --input path    Path for input (required)
  |  -o | --on | --output path   Path for output (required)
  |""".stripMargin

  def quit(status: Int = 0, message: String = ""): Nothing =         // <1>
    if message.length > 0 then println("ERROR: "+message)
    println(help)
    sys.exit(status)

  case class Args(inputPath: Option[String], outputPath: Option[String])

  def parseArgList(params: Array[String]): Args =                    // <2>
    def pa(params2: Seq[String], args: Args): Args = params2 match   // <3>
      case Nil => args                                               // <4>
      case ("-h" | "--help") +: Nil => quit()                        // <5>
      case ("-i" | "--in" | "--input") +: path +: tail =>            // <6>
        pa(tail, args.copy(inputPath = Some(path)))                  // <7>
      case ("-o" | "--out" | "--output") +: path +: tail =>          // <8>
        pa(tail, args.copy(outputPath = Some(path)))
      case _ => quit(1, s"Unrecognized argument ${params2.head}")    // <9>

    val argz = pa(params.toList, Args(None, None))                   // <10>
    if argz.inputPath == None || argz.outputPath == None then        // <11>
      quit(1, "Must specify input and output paths.")
    argz

  def main(params: Array[String]): Unit =
    val argz = parseArgList(params)
    println(argz)
