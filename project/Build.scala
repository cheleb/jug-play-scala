import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "jug-play-scala"
  val appVersion = "1.0"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "com.typesafe.slick" %% "slick" % "1.0.0",
    "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
    jdbc)

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
    playAssetsDirectories <+= baseDirectory / "packages",
    playAssetsDirectories <+= baseDirectory / "dart"
    )
}
