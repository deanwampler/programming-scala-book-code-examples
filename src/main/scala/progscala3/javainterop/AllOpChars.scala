// src/main/scala/progscala3/javainterop/AllOpChars.scala
package progscala3.javainterop
import scala.annotation.alpha

trait AllOpChars {
  @alpha("eqeq")       def == : Int   // $eq$eq  - arbitrary return type
  @alpha("greater")    def >  : Int   // $greater
  @alpha("less")       def <  : Int   // $less
  @alpha("plus")       def +  : Int   // $plus
  @alpha("minus")      def -  : Int   // $minus
  @alpha("times")      def *  : Int   // $times
  @alpha("div")        def /  : Int   // $div
  @alpha("bslash")     def \  : Int   // $bslash
  @alpha("bar")        def |  : Int   // $bar
  @alpha("bang")       def !  : Int   // $bang
  @alpha("qmark")      def ?  : Int   // $qmark
  @alpha("coloncolon") def :: : Int   // $colon$colon
  @alpha("percent")    def %  : Int   // $percent
  @alpha("up")         def ^  : Int   // $up
  @alpha("amp")        def &  : Int   // $amp
  @alpha("atat")       def @@ : Int   // $at$at
  // @alpha("hashhash")   def ## : Int // $hash$hash (already in AnyRef)
  @alpha("tilde")      def ~  : Int   // $tilde
}
