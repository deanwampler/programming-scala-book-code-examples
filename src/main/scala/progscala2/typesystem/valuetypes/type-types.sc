// src/main/scala/progscala2/typesystem/valuetypes/type-types.sc
import progscala2.typesystem.valuetypes._

val s11 = new Service1
val s12 = new Service1

val l1: Logger = s11.logger                      // Okay
val l2: Logger = s12.logger                      // Okay

val l11: s11.logger.type = s11.logger            // Okay
val l12: s11.logger.type = s12.logger            // ERROR
