// src/script/scala/progscala3/contexts/ByNameContextParameters.scala

trait Codec[T]:
  def write(x: T): Unit

given intCodec as Codec[Int]:
  def write(i: Int): Unit = print(i)

given optionCodec[T](using ev: => Codec[T]) as Codec[Option[T]]:
  def write(xo: Option[T]) = xo match
    case Some(x) => ev.write(x)
    case None =>

val s = summon[Codec[Option[Int]]]

s.write(Some(33))
s.write(None)
