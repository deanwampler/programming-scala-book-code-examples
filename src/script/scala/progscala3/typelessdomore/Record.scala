// src/script/scala/progscala3/typelessdomore/Record.scala

type Record = (String, Double)
def transform(record: Record): Record = (record._1.toUpperCase, 2*record._2)

transform(("hello", 10))
