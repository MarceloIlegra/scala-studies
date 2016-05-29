name := "futures"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "Local Maven Repository" at "" + Path.userHome.asFile.toURI.toURL + "/.m2/repository"
)
resolvers ++= Seq(Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.bintrayRepo("scalaz", "releases"),
  Resolver.bintrayRepo("megamsys", "scala"))

libraryDependencies ++= {
  Seq(
    "io.megam" %% "newman" % "1.3.12",
    "org.json4s" % "json4s-jackson_2.11" % "3.2.10",
    "org.json4s" % "json4s-native_2.11" % "3.2.10"
  )
}