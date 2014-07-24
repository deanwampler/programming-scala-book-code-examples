// src/main/scala/TypeSystem/selftype/selftype-cake-pattern.sc

trait Persistence { def startPersistence(): Unit }         // <1>
trait Midtier { def startMidtier(): Unit }                 // <2>
trait UI { def startUI(): Unit }                           // <3>

trait Database extends Persistence {                       // <4>
  def startPersistence(): Unit = println("Starting Database")  
}
trait ComputeCluster extends Midtier {
  def startMidtier(): Unit = println("Starting ComputeCluster")  
}
trait WebUI extends UI {
  def startUI(): Unit = println("Starting WebUI")  
}

trait App { self: Persistence with Midtier with UI =>      // <5>
  
  def run() = {
    startPersistence()
    startMidtier()
    startUI()
  }
}

object MyApp extends App with Database with ComputeCluster with WebUI
                                                           // <6>
MyApp.run                                                  // <7>
