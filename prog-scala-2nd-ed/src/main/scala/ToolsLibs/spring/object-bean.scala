// code-examples/ToolsLibs/spring/object-bean.scala

package toolslibs.spring

case class NamedObject(name: String)

trait Factory {
  @scala.reflect.BeanProperty
  var nameOfFactory = "unknown"

  def make(name: String): AnyRef
}

object NamedObjectFactory extends Factory {
  def make(name: String) = NamedObject(name) 
}

case class FactoryUsingBean(factory: Factory)
