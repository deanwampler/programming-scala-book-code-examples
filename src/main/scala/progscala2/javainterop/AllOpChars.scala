// src/main/scala/progscala2/javainterop/AllOpChars.scala
package progscala2.javainterop

trait AllOpChars {
  def == : Int   // $eq$eq  - arbitrary return type
  def >  : Int   // $greater
  def <  : Int   // $less
  def +  : Int   // $plus
  def -  : Int   // $minus
  def *  : Int   // $times
  def /  : Int   // $div
  def \  : Int   // $bslash
  def |  : Int   // $bar
  def !  : Int   // $bang
  def ?  : Int   // $qmark
  def :: : Int   // $colon$colon
  def %  : Int   // $percent
  def ^  : Int   // $up
  def &  : Int   // $amp
  def @@ : Int   // $at$at
//  def ## : Int   // $hash$hash (there is already a ## method in AnyRef)
  def ~  : Int   // $tilde
}
