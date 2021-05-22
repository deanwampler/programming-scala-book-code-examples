// src/script/scala/progscala3/typelessdomore/Record.scala

type Rec = (String, Double)
def transform(record: Rec): Rec = (record._1.toUpperCase, 2*record._2)
transform(("hello", 10))
