javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions ++= Seq("-target:jvm-1.7")

resolvers += Resolver.file("Local Repository", file("/Users/olivier/projects/scala/Play20-myfork/repository/local"))(Resolver.ivyStylePatterns)

//mainDarts := Seq("sosimple.dart")

//DartPlugin.dartSettings
