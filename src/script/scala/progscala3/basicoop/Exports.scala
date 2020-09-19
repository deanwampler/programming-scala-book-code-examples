// tag::traits[]
// src/script/scala/progscala3/basicoop/Exports.scala

case class UserName(value: String):                                  // <1>
  assert(value.length > 0)
case class Password(value: String):
  assert(value.length > 0)

trait Authenticate:
  final def apply(                                                   // <2>
      username: UserName, password: Password): Boolean =
    authenticated = auth(username, password)
    authenticated
  def isAuthenticated: Boolean = authenticated

  private var authenticated = false   //
  protected def auth(
      username: UserName, password: Password): Boolean

class DirectoryAuthenticate extends Authenticate:
  protected def auth(
    username: UserName, password: Password): Boolean = true

class ResourceManager(                                               // <3>
    private var resources: Map[String,Any] = Map.empty):
  def getResource(key: String): Option[Any] = resources.get(key)
  def setResource(key: String, value: Any) = resources += (key -> value)
  def deleteResource(key: String) = resources -= key

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait AsyncWorker[A,B]:                                              // <4>
  def apply(data: A): Future[B]

class AsyncTokenizer extends AsyncWorker[String, Seq[String]]:
  def apply(string: String): Future[Seq[String]] =
    Future(string.split("""\W+""").toSeq)
// end::traits[]

// tag::service1[]
object ServiceWithoutExports:
  private val dirAuthenticate = new DirectoryAuthenticate
  private val manager = new ResourceManager(sys.env)                 // <1>
  private val tokenizer = new AsyncTokenizer

  def authenticate(username: UserName, password: Password): Boolean =
    dirAuthenticate(username, password)
  def isAuthenticated: Boolean = dirAuthenticate.isAuthenticated
  def getResource(key: String): Option[Any] = manager.getResource(key)
  def tokenize(string: String): Future[Seq[String]] = tokenizer(string)
// end::service1[]

// tag::service2[]
object Service:
  private val dirAuthenticate = new DirectoryAuthenticate
  private val manager = new ResourceManager(sys.env)
  private val tokenizer = new AsyncTokenizer

  export dirAuthenticate.{apply => authenticate, isAuthenticated}
  export manager.getResource
  export tokenizer.{apply => tokenize}
// end::service2[]

// tag::example[]
Service.isAuthenticated
Service.authenticate(UserName("Buck Trends"), Password("1234"))
Service.isAuthenticated
Service.getResource("HOME")
val tokensF = Service.tokenize("Hello from the World!")
tokensF.value
// end::example[]
