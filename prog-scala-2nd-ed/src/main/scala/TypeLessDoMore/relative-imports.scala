// code-examples/TypeLessDoMore/relative-imports.scala

import scala.collection.mutable._
import collection.immutable._         // Since "scala" is already imported
import _root_.scala.collection.parallel._  // full path from real "root"

package scala.actors {
  import remote._                     // We're in the scope of "scala.actors"
}
