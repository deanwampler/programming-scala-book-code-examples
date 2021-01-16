// src/script/scala/progscala3/contexts/ObjectExtensionMethods.scala

object Foo:
  def one: Int = 1

extension (foo: Foo.type)
  def add(i: Int): Int = i + foo.one

Foo.one
Foo.add(10)
