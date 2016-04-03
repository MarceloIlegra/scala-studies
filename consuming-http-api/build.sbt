name := "consuming-http-api"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.bintrayRepo("scalaz", "releases"),
  Resolver.bintrayRepo("megamsys", "scala"))

libraryDependencies += "io.megam" %% "newman" % "1.3.12"
libraryDependencies += "org.json4s" % "json4s-native_2.11" % "3.2.10"
libraryDependencies += "org.json4s" % "json4s-jackson_2.11" % "3.2.10"

    