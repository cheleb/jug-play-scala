javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions ++= Seq("-target:jvm-1.7")

resolvers += "Spray IO" at "http://repo.spray.io/" 

mainDarts := Seq("sosimple.dart")
