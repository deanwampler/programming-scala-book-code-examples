resolvers ++= Seq(
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.4.6")
