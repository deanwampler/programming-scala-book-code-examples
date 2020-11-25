// src/main/scala/progscala3/javainterop/AllOpChars.scala
package progscala3.javainterop
import scala.annotation.targetName

trait AllOpChars:
  @targetName("eqeq")       def == : Int   // $eq$eq  - arbitrary return type
  @targetName("greater")    def >  : Int   // $greater
  @targetName("less")       def <  : Int   // $less
  @targetName("plus")       def +  : Int   // $plus
  @targetName("minus")      def -  : Int   // $minus
  @targetName("times")      def *  : Int   // $times
  @targetName("div")        def /  : Int   // $div
  @targetName("bslash")     def \  : Int   // $bslash
  @targetName("bar")        def |  : Int   // $bar
  @targetName("bang")       def !  : Int   // $bang
  @targetName("qmark")      def ?  : Int   // $qmark
  @targetName("coloncolon") def :: : Int   // $colon$colon
  @targetName("percent")    def %  : Int   // $percent
  @targetName("up")         def ^  : Int   // $up
  @targetName("amp")        def &  : Int   // $amp
  @targetName("atat")       def @@ : Int   // $at$at
  // @targetName("hashhash")   def ## : Int // $hash$hash (already in AnyRef)
  @targetName("tilde")      def ~  : Int   // $tilde
