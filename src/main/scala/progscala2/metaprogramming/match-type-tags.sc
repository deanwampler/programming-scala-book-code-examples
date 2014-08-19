// src/main/scala/progscala2/metaprogramming/match-type-tags.sc
import scala.reflect.runtime.universe._                              // <1>

def toType2[T](t: T)(implicit tag: TypeTag[T]): Type = tag.tpe       // <2>
def toType[T : TypeTag](t: T): Type = typeOf[T]                      // <3>

/**
 * Return a tuple of the type "prefix", the type's symbol and its
 * possibly empty list of type parameters.
 */
def toTypeRefInfo[T : TypeTag](x: T): (Type, Symbol, Seq[Type]) = {  // <4>
  val TypeRef(pre, typName, parems) = toType(x)
  (pre, typName, parems)
}

toType(1)
toType(true)
toType(Seq(1, true, 3.14))
toType((i: Int) => i.toString)

toType(1) <:< typeOf[AnyVal]           // => true
toType(1) <:< toType(1)                // => true
toType(1) <:< toType(true)             // => false

toType(1) =:= typeOf[AnyVal]           // => false
toType(1) =:= toType(1)                // => true
toType(1) =:= toType(true)             // => false

toTypeRefInfo(1)                       // (scala.type, class Int, List())
toTypeRefInfo(true)                    // (scala.type, class Boolean, List())
toTypeRefInfo(Seq(1, true, 3.14))      // (scala.collection.type, trait Seq,
                                       //    List(AnyVal))
toTypeRefInfo((i: Int) => i.toString)  // (scala.type, trait Function1,
                                       //    List(Int, java.lang.String))

val t1 = toType(1)
val ts = toType(Seq(1, true, 3.14))
val tf = toType((i: Int) => i.toString)

// Use the reflect.api.types$TypeApi:
t1.typeSymbol
ts.typeSymbol
tf.typeSymbol

t1.erasure
ts.erasure
tf.erasure

t1.typeArgs
ts.typeArgs
tf.typeArgs

t1.typeParams
ts.typeParams
tf.typeParams

t1.baseClasses
ts.baseClasses
tf.baseClasses

t1.companion
ts.companion
tf.companion

t1.decls
ts.decls
tf.decls

t1.members
ts.members
tf.members

