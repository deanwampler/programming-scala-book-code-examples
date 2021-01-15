// src/script/scala/progscala3/patternmatching/AssignmentsInterpStrs.scala

val str = """Book: "Programming Scala", by Dean Wampler"""
val s"""Book: "$title", by $author""" = str : @unchecked
assert(title == "Programming Scala" && author == "Dean Wampler")
