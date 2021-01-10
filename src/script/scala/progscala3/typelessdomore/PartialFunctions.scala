// tag::defs[]
// src/script/scala/progscala3/typelessdomore/PartialFunctions.scala

val pfs: PartialFunction[Matchable,String] =                         // <1>
  case s:String => "YES"
val pfd: PartialFunction[Matchable,String] =                         // <2>
  case d:Double => "YES"

val pfsd = pfs.orElse(pfd)                                           // <3>
// end::defs[]

// tag::examples[]
def tryPF(
    x: Matchable, f: PartialFunction[Matchable,String]): String =
  try f(x)
  catch case _: MatchError => "ERROR!"

assert(tryPF("str", pfs)  == "YES")
assert(tryPF("str", pfd)  == "ERROR!")
assert(tryPF("str", pfsd) == "YES")
assert(tryPF(3.142, pfs)  == "ERROR!")
assert(tryPF(3.142, pfd)  == "YES")
assert(tryPF(3.142, pfsd) == "YES")
assert(tryPF(2, pfs)      == "ERROR!")
assert(tryPF(2, pfd)      == "ERROR!")
assert(tryPF(2, pfsd)     == "ERROR!")

assert(pfs.isDefinedAt("str")  == true)
assert(pfd.isDefinedAt("str")  == false)
assert(pfsd.isDefinedAt("str") == true)
assert(pfs.isDefinedAt(3.142)  == false)
assert(pfd.isDefinedAt(3.142)  == true)
assert(pfsd.isDefinedAt(3.142) == true)
assert(pfs.isDefinedAt(2)      == false)
assert(pfd.isDefinedAt(2)      == false)
assert(pfsd.isDefinedAt(2)     == false)
// end::examples[]

// tag::examples2[]
val fs = pfs.lift
assert(fs("str") == Some("YES"))
assert(fs(3.142) == None)

val pfs2 = fs.unlift
assert(pfs2("str") == "YES")
val trypi = tryPF(3.142, pfs2)  // Use tryPF we defined above
assert(trypi == "ERROR!")
// end::examples2[]

