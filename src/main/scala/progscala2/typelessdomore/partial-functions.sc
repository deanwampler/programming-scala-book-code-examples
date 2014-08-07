// src/main/scala/progscala2/typelessdomore/partial-functions.sc

val pf1: PartialFunction[Any,String] = { case s:String => "YES" }    // <1>
val pf2: PartialFunction[Any,String] = { case d:Double => "YES" }    // <2>

val pf = pf1 orElse pf2                                              // <3>

def tryPF(x: Any, f: PartialFunction[Any,String]): String =          // <4>
  try { f(x).toString } catch { case _: MatchError => "ERROR!" }

def d(x: Any, f: PartialFunction[Any,String]) =                      // <5>
  f.isDefinedAt(x).toString

println("      |   pf1 - String  |   pf2 - Double  |    pf - All")   // <6>
println("x     | def?  |  pf1(x) | def?  |  pf2(x) | def?  |  pf(x)")
println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
List("str", 3.14, 10) foreach { x =>
  printf("%-5s | %-5s | %-6s  | %-5s | %-6s  | %-5s | %-6s\n", x.toString, 
    d(x,pf1), tryPF(x,pf1), d(x,pf2), tryPF(x,pf2), d(x,pf), tryPF(x,pf))
}

