// code-examples/XML/writing/post.scala

import java.text.SimpleDateFormat
import java.util.Date

class Post(val title: String, val body: String, val updated: Date) {
  lazy val dashedDate = {
    val dashed = new SimpleDateFormat("yy-MM-dd")
    dashed.format(updated)
  }

  lazy val atomDate = {
    val rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'h:m:ss'-05:00'")
    rfc3339.format(updated)
  }

  lazy val slug = title.toLowerCase.replaceAll("\\W", "-")
  lazy val atomId  = "tag:example.com," + dashedDate + ":/" + slug
}