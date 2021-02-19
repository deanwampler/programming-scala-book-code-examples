// tag::traits[]
// src/script/scala/progscala3/basicoop/Exports.scala

import java.net.URL

case class UserName(value: String):                                  // <1>
  assert(value.length > 0)
case class Password(value: String):
  assert(value.length > 0)

trait Authenticate:                                                  // <2>
  final def apply(
      username: UserName, password: Password): Boolean =
    authenticated = auth(username, password)
    authenticated
  def isAuthenticated: Boolean = authenticated

  private var authenticated = false
  protected def auth(username: UserName, password: Password): Boolean

class DirectoryAuthenticate(location: URL) extends Authenticate:
  protected def auth(username: UserName, password: Password): Boolean = true
// end::traits[]

// tag::service1[]
object ServiceWithoutExports:
  private val dirAuthenticate =
    DirectoryAuthenticate(URL("https://directory.wtf"))

  def authenticate(username: UserName, password: Password): Boolean =
    dirAuthenticate(username, password)
  def isAuthenticated: Boolean = dirAuthenticate.isAuthenticated
// end::service1[]

// tag::service2[]
object Service:
  private val dirAuthenticate =
    DirectoryAuthenticate(URL("https://directory.wtf"))

  export dirAuthenticate.{isAuthenticated, apply as authenticate}
// end::service2[]

// tag::example[]
Service.isAuthenticated
Service.authenticate(UserName("Buck Trends"), Password("1234"))
Service.isAuthenticated
// end::example[]
