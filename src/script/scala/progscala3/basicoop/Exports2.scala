// src/script/scala/progscala3/basicoop/Exports2.scala

// This version of Exports.scala doesn't appear in the book, but it
// includes more examples of exporting parts of internal dependencies.

import java.net.URL

case class UserName(value: String):
  assert(value.length > 0)
case class Password(value: String):
  assert(value.length > 0)

trait Authenticate:
  final def apply(
      username: UserName, password: Password): Boolean =
    authenticated = auth(username, password)
    authenticated
  def isAuthenticated: Boolean = authenticated

  private var authenticated = false
  protected def auth(username: UserName, password: Password): Boolean

class DirectoryAuthenticate(location: URL) extends Authenticate:
  protected def auth(username: UserName, password: Password): Boolean = true

class ResourceManager(
    private var resources: Map[String,Any] = Map.empty):
  def getResource(key: String): Option[Any] = resources.get(key)
  def setResource(key: String, value: Any) = resources += (key -> value)
  def deleteResource(key: String) = resources -= key

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait AsyncWorker[A,B]:
  def apply(data: A): Future[B]

class AsyncTokenizer extends AsyncWorker[String, Seq[String]]:
  def apply(string: String): Future[Seq[String]] =
    Future(string.split("""\W+""").toSeq)

object ServiceWithoutExports:
  private val dirAuthenticate =
    DirectoryAuthenticate(URL("https://directory.wtf"))
  private val manager = ResourceManager(sys.env)
  private val tokenizer = AsyncTokenizer()

  def authenticate(username: UserName, password: Password): Boolean =
    dirAuthenticate(username, password)
  def isAuthenticated: Boolean = dirAuthenticate.isAuthenticated
  def getResource(key: String): Option[Any] = manager.getResource(key)
  def tokenize(string: String): Future[Seq[String]] = tokenizer(string)
// end::service1[]

// tag::service2[]
object Service:
  private val dirAuthenticate =
    DirectoryAuthenticate(URL("https://directory.wtf"))
  private val manager = ResourceManager(sys.env)
  private val tokenizer = AsyncTokenizer()

  export dirAuthenticate.{isAuthenticated, apply as authenticate}
  export manager.getResource
  export tokenizer.apply as tokenize
// end::service2[]

// tag::example[]
Service.isAuthenticated
Service.authenticate(UserName("Buck Trends"), Password("1234"))
Service.isAuthenticated
Service.getResource("HOME")
val tokensF = Service.tokenize("Hello from the World!")
tokensF.value
// end::example[]
