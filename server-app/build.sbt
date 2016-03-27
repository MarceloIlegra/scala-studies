name := "server-app"

version := "1.0"

scalaVersion := "2.11.5"

//addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

resolvers ++= Seq(
  "Local Maven Repository" at "" + Path.userHome.asFile.toURI.toURL + "/.m2/repository",
  "Twitter" at "http://maven.twttr.com"
)

libraryDependencies ++= {
  val finatraV = "2.1.1"
  Seq(
    "com.twitter.finatra"   %%   "finatra-http"    % finatraV,
    "com.twitter.finatra"   %%   "finatra-slf4j"   % finatraV,
    "com.twitter.inject"    %%   "inject-core"     % finatraV
  //  "ch.qos.logback"        %    "logback-classic" % "1.0.13"
  )
}
