import sbt.Keys._

name := "starwars"

version := "1.0"

scalaVersion := "2.11.8"


resolvers ++= Seq(
  "Local Maven Repository" at "" + Path.userHome.asFile.toURI.toURL + "/.m2/repository",
  "Twitter" at "http://maven.twttr.com"
)
resolvers ++= Seq(Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.bintrayRepo("scalaz", "releases"),
  Resolver.bintrayRepo("megamsys", "scala"))

libraryDependencies ++= {
  val finatraV = "2.1.1"
  Seq(
    "com.twitter.finatra"   %%   "finatra-http"    % finatraV,
    "com.twitter.finatra"   %%   "finatra-slf4j"   % finatraV,
    "com.twitter.inject"    %%   "inject-core"     % finatraV,
    "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test",
    "io.megam" %% "newman" % "1.3.12",
    "org.json4s" % "json4s-jackson_2.11" % "3.2.10",
    "org.json4s" % "json4s-native_2.11" % "3.2.10"
  )
}


