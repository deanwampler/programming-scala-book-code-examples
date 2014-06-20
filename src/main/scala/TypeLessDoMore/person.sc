// src/main/scala/TypeLessDoMore/person.sc

class Person(val firstName: String, val lastName: String, var age: Int)

val p = new Person("Dean", "Wampler", 29)

p.firstName
p.lastName
p.age

p.firstName = "Buck"
p.lastName = "Trends"
p.age = 30

p


