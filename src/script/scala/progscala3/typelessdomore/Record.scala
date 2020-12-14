// src/script/scala/progscala3/typelessdomore/Record.scala

object Rec:
  type Record = (String, Double)
  def transform(record: Record): Record =
    (record._1.toUpperCase, 2*record._2)

Foo.transform(Rec.Record("hello", 10))
val res0: Foo.Record = (HELLO,20.0)
