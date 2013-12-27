// src/main/scala/BasicOOP/ui/widget.scala

package basicoop.ui

abstract class Widget {
  class Properties {
    import scala.collection.immutable.HashMap

    private var values: Map[String, Any] = new HashMap

    def size = values.size

    def get(key: String) = values.get(key)

    def update(key: String, value: Any) = {
      // Do some preprocessing, e.g., filtering.
      values = values.updated(key, value)
      // Do some postprocessing.
    }
  }

  val properties = new Properties
}
