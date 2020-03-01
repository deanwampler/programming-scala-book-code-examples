// src/main/scala/progscala3/typelessdomore/partial-functions.sc

val pfs: PartialFunction[Any,String] = { case s:String => "YES" }    // <1>
val pfd: PartialFunction[Any,String] = { case d:Double => "YES" }    // <2>

val pfsd = pfs orElse pfd                                            // <3>

def tryPF(x: Any, f: PartialFunction[Any,String]): String =          // <4>
  try { 
    f(x).toString 
  } catch { 
    case _: MatchError => "ERROR!" 
  }

def isDef(x: Any, f: PartialFunction[Any,String]) =                  // <5>
  f.isDefinedAt(x).toString

def line[T](t: T): String = 
  "| %-4s | %-5s  | %-6s | %-5s  | %-6s | %-5s  | %-6s |".format(
    t.toString, isDef(t,pfs), tryPF(t,pfs), isDef(t,pfd), tryPF(t,pfd), 
    isDef(t,pfsd), tryPF(t,pfsd))

println(                                                             // <6>
  s"""
  ?|      |  pfs - String   |  pfd - Double   |   pfsd - All    |
  ?| x    | isDef? | pfs(x) | isDef? | pfd(x) | isDef? | pf(x)  |
  ?++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  ?${line("str")}
  ?${line(3.14)}
  ?${line(10)}
  ?++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  ?""".stripMargin('?'))
