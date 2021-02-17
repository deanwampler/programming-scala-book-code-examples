// src/main/scala-2/progscala3/meta/MatchTypeTags.scala

package progscala3.meta

import scala.reflect.runtime.universe.*

/**
 * This example uses TypeTags, which don't exist in Scala 3.
 */
object MatchTypeTags {

  def toType2[T](t: T)(implicit tag: TypeTag[T]): Type = tag.tpe
  def toType[T : TypeTag](t: T): Type = typeOf[T]

  /**
   * Return a tuple of the type "prefix", the type's symbol and its
   * possibly empty list of type parameters.
   .*/
  def toTypeRefInfo[T : TypeTag](x: T): (Type, Symbol, Seq[Type]) = {
    val TypeRef(pre, typName, parems) = toType(x)
    (pre, typName, parems)
  }

  def main(params: Array[String]): Unit = {
    println(toType(1))
    println(toType(true))
    println(toType(Seq(1, true, 3.14)))
    println(toType((i: Int) => i.toString))

    println(toType2(1))
    println(toType2(true))
    println(toType2(Seq(1, true, 3.14)))
    println(toType2((i: Int) => i.toString))

    assert(toType(1) <:< typeOf[AnyVal] == true)
    assert(toType(1) <:< toType(1)      == true)
    assert(toType(1) <:< toType(true)   == false)

    assert(toType(1) =:= typeOf[AnyVal] == false)
    assert(toType(1) =:= toType(1)      == true)
    assert(toType(1) =:= toType(true)   == false)

    // (scala.type, class Int, List()):
    println(toTypeRefInfo(1))

    // (scala.type, class Boolean, List()):
    println(toTypeRefInfo(true))

    // (scala.collection.type, trait Seq, List(AnyVal)):
    println(toTypeRefInfo(Seq(1, true, 3.14)))

    // (scala.type, trait Function1, List(Int, java.lang.String)):
    println(toTypeRefInfo((i: Int) => i.toString))

    val t1 = toType(1)
    val ts = toType(Seq(1, true, 3.14))
    val tf = toType((i: Int) => i.toString)

    // Use the reflect.api.types$TypeApi. (The output is long!)
    def p(msg: String, t: Type): Unit = {
      println(s"""
        |For input $msg:
        |  t.typeSymbol:  ${t.typeSymbol}
        |  t.erasure:     ${t.erasure}
        |  t.typeArgs:    ${t.typeArgs}
        |  t.typeParams:  ${t.typeParams}
        |  t.companion:   ${t.companion}
        |  t.decls:       ${t.decls}
        |  t.members:     ${t.members}
        |""".stripMargin)
    }

    p("1:Int", t1)
    p("Seq(1, true, 3.14)", ts)
    p("i:Int => String", tf)
  }
}
