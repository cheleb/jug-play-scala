//scalaVersion := "2.10.0"

// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += Resolver.file("Local Repository", file("/Users/olivier/projects/scala/Play20-myfork/repository/local"))(Resolver.ivyStylePatterns)

// Use the Play sbt plugin for Play projects
//addSbtPlugin("play" % "sbt-plugin" % "2.2-SNAPSHOT")
addSbtPlugin("play" % "sbt-plugin" % "2.1.0")

addSbtPlugin("net.orcades" % "sbt-dart-plugin" % "0.2.1-SNAPSHOT")
