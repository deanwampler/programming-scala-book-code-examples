// src/main/scala/progscala2/typesystem/selftype/selftype-cake-pattern.sc

trait Persistence { def startPersistence(): Unit }                   // <1>
trait Midtier { def startMidtier(): Unit }
trait UI { def startUI(): Unit }

trait Database extends Persistence {                                 // <2>
  def startPersistence(): Unit = println("Starting Database")  
}
trait BizLogic extends Midtier {
  def startMidtier(): Unit = println("Starting BizLogic")  
}
trait WebUI extends UI {
  def startUI(): Unit = println("Starting WebUI")  
}

trait App { self: Persistence with Midtier with UI =>                // <3>
  
  def run() = {
    startPersistence()
    startMidtier()
    startUI()
  }
}

object MyApp extends App with Database with BizLogic with WebUI      // <4>
                                                                     
MyApp.run                                                            // <5>
