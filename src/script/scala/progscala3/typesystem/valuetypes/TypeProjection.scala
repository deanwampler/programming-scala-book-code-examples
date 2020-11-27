// src/script/scala/progscala3/typesystem/valuetypes/TypeProjection.scala
import progscala3.typesystem.valuetypes._

// The first two attempts try to reference a member of a value, but these are
// types, not values:
val l1: Service.Log        = new ConsoleLogger  // ERROR: No Service
val l2: ConsoleService.Log = new ConsoleLogger  // ERROR: No ConsoleService

// This attempt doesn't type check:

val l3: Service#Log        = new ConsoleLogger

// Success!
val l4: ConsoleService#Log = new ConsoleLogger

