// src/script/scala/progscala3/typesystem/selftype/SelfTypeCakePattern.scala
// This example doesn't appear in the book, but it is mentioned there.

// Traits defining major components:
trait Persistence:
  def startPersistence(): String
trait Midtier:
  def startMidtier(): String
trait UI:
  def startUI(): String

// Implementations of those abstractions:
trait Database extends Persistence:
  def startPersistence(): String = "Starting Database"
trait BizLogic extends Midtier:
  def startMidtier(): String = "Starting BizLogic"
trait WebUI extends UI:
  def startUI(): String = "Starting WebUI"

// The "application", composed of the components:
trait App:
  // The self-type annotation specifies what traits that must be mixed into
  // App to create concrete instances:
  self: Persistence & Midtier & UI =>

  // Call methods that exist in the mixin traits. The self-type annotation
  // ensures they exist.
  def run(): Seq[String] =
    Seq(startPersistence(),
      startMidtier(),
      startUI())

// The concrete application that wires the components together:
object MyApp extends App with Database with BizLogic with WebUI

assert(MyApp.run() ==
  Seq("Starting Database", "Starting BizLogic", "Starting WebUI"))
