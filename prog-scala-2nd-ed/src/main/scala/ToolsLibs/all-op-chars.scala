// code-examples/ToolsLibs/all-op-chars.scala

package toolslibs

trait AllOpChars {
  def == : Unit   // $eq$eq
  def >  : Unit   // $greater
  def <  : Unit   // $less
  def +  : Unit   // $plus
  def -  : Unit   // $minus
  def *  : Unit   // $times
  def /  : Unit   // $div
  def \  : Unit   // $bslash
  def |  : Unit   // $bar
  def !  : Unit   // $bang
  def ?  : Unit   // $qmark
  def :: : Unit   // $colon$colon
  def %  : Unit   // $percent
  def ^  : Unit   // $up
  def &  : Unit   // $amp
  def @@ : Unit   // $at$at
//def ## : Unit   // $hash$hash (there is already a ## method in AnyRef)
  def ~  : Unit   // $tilde
}
