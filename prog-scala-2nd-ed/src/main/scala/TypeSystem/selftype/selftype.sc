// code-examples/TypeSystem/selftype/selftype-script.scala

trait Persistence {
  def startPersistence: Unit
}

trait Midtier {
  def startMidtier: Unit
}

trait UI {
  def startUI: Unit
}

trait Database extends Persistence {
  def startPersistence = println("Starting Database")  
}

trait ComputeCluster extends Midtier {
  def startMidtier = println("Starting ComputeCluster")  
}

trait WebUI extends UI {
  def startUI = println("Starting WebUI")  
}

trait App {
  self: Persistence with Midtier with UI =>
  
  def run = {
    startPersistence
    startMidtier
    startUI
  }
}

object MyApp extends App with Database with ComputeCluster with WebUI

MyApp.run