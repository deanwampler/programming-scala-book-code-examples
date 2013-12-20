// code-examples/TypeLessDoMore/map-get-script.scala
// Very limited version of a map; it can hold only one key-value
// pair! The "get" method is used in the text, by itself...

class MyMap[A,B](var _key: A, var _value: B) {
  def get(key: A): Option[B] = {
    if (contains(key))
      Some(getValue(key))
    else
      None
  }
  def contains(key: A) = key == _key
  def getValue(key: A) = _value
  def put(key: A, value: B) = _value = value
}

val m = new MyMap(1, "one")
println( m.get(1) )