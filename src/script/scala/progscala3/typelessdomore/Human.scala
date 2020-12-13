// src/script/scala/progscala3/typelessdomore/Human.scala

class Human(val name: String, var age: Int)

val p = new Human("Dean Wampler", 29)

p.name
p.name = "Buck Trends"
p.name
p.age
p.age = 30
p.age
p.age = 31; p.age  // Use semicolon to join two expressions...
