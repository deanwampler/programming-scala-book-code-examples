// src/main/scala/progscala3/typesystem/selftype/selftype-cake-pattern.sc

trait Persistence { def startPersistence(): String }                 // <1>
trait Midtier { def startMidtier(): String }
trait UI { def startUI(): String }

trait Database extends Persistence {                                 // <2>
  def startPersistence(): String = "Starting Database"  
}
trait BizLogic extends Midtier {
  def startMidtier(): String = "Starting BizLogic"  
}
trait WebUI extends UI {
  def startUI(): String = "Starting WebUI"  
}

trait App { self: Persistence with Midtier with UI =>                // <3>
  
  def run(): Seq[String] = {
    Seq(startPersistence(),                                          // <4>
      startMidtier(),
      startUI())
  }
}

object MyApp extends App with Database with BizLogic with WebUI      // <5>
                                                                     
assert(MyApp.run() ==                                                // <6>
  Seq("Starting Database", "Starting BizLogic", "Starting WebUI"))
