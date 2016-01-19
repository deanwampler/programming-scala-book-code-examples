// src/main/scala/progscala2/typelessdomore/person.sc

class Person(val name: String, var age: Int)

val p = new Person("Dean Wampler", 29)

p.name
p.age

p.name = "Buck Trends"
p.age = 30

p


