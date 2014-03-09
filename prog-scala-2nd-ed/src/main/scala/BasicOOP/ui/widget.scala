// src/main/scala/BasicOOP/ui/widget.scala

package oop.ui

abstract class Widget {

  class Properties {

    private var values = Map.empty[String, Any]

    def size = values.size

    def get(key: String): Any = values.get(key)

    def update(key: String, value: Any): Unit = {
      // Do some preprocessing, e.g., filtering.
      values = values.updated(key, value)
      // Do some postprocessing.
    }
  }

  val properties = new Properties
}
