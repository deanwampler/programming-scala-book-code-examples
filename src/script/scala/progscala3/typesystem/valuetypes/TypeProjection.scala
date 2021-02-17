// src/script/scala/progscala3/typesystem/valuetypes/TypeProjection.scala
import progscala3.typesystem.valuetypes.*

// The first two attempts try to reference a member of a value, but these are
// types, not values:
val l1: Service.Log        = ConsoleLogger()  // ERROR: No Service
val l2: ConsoleService.Log = ConsoleLogger()  // ERROR: No ConsoleService
val l3: Service#Log        = ConsoleLogger()  // ERROR: Doesn't type check:
val l4: ConsoleService#Log = ConsoleLogger()  // Success!

