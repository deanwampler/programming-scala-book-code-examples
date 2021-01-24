// src/script/scala/progscala3/typelessdomore/MapGet.scala
// Very limited version of a map; it can hold only one key-value
// pair! The "get" method is used in the text, by itself...

class MyMap[A,B](var _key: A, var _value: B):
  def get(key: A): Option[B] =
    if contains(key) then Some(getValue(key))
    else None

  def contains(key: A): Boolean = key == _key
  def getValue(key: A): B =
    if contains(key) then _value
    else throw IllegalArgumentException(s"No value for key $key")

  def put(key: A, value: B): Unit =
    _key   = key
    _value = value

val m = MyMap(1, "one")
assert(m.contains(1) == true)
assert(m.contains(2) == false)
assert(m.get(1) == Some("one"))
assert(m.get(2) == None)
assert(m.getValue(1) == "one")
try
  m.getValue(2)
  assert(false, "Shouldn't get here!")
catch
  case iae: IllegalArgumentException => // expected
  case util.control.NonFatal(nfe) =>
    assert(false, s"Unexpected exception: $nfe")
