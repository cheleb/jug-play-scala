import sbt._
import Keys._
import play.Project._



object ApplicationBuild extends Build {

    val appName         = "jug-play-scala"
    val appVersion      = "1.0"

    val appDependencies = Seq(
      // Add your project dependencies here,
        "postgresql" % "postgresql" % "9.1-901.jdbc4",
        "com.typesafe.slick" %% "slick" % "1.0.0",
        "io.spray" %%  "spray-json" % "1.2.3",
        jdbc
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here      
    )

}
