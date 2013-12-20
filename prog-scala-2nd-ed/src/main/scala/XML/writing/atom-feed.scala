// code-examples/XML/writing/atom-feed.scala

import scala.xml.XML

class AtomFeed(posts: Seq[Post]) {
  val feed =
  <feed xmlns="http://www.w3.org/2005/Atom">
    <title>My Blog</title>
    <subtitle>A fancy subtitle.</subtitle>
    <link href="http://example.com/"/>
    <link href="http://example.com/atom.xml" rel="self"/>
    <updated>{posts(0).atomDate}</updated>
    <author>
      <name>John Doe</name>
      <uri>http://example.com/about.html</uri>
    </author>
    <id>http://example.com/</id>
    {for (post <- posts) yield
    <entry>
      <title>{post.title}</title>
      <link href={"http://example.com/" + post.slug + ".html"} rel="alternate"/>
      <id>{post.atomId}</id>
      <updated>{post.atomDate}</updated>
      <content type="html">{post.body}</content>
      <author>
        <name>John Doe</name>
        <uri>http://example.com/about.html</uri>
      </author>
    </entry>
    }
  </feed>

  def write = XML.save(Config.atomPath, feed, "UTF-8", true, null)
}
