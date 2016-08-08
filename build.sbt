name := "scala"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
"jcenter Repository" at  "http://jcenter.bintray.com/"
)
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.9-RC1"

libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.4.9-RC1"

libraryDependencies +=  "commons-io" % "commons-io" % "2.4"

libraryDependencies +=  "org.scala-lang.modules" %% "scala-swing" % "1.0.2"

libraryDependencies +=  "org.scala-stm" %% "scala-stm" % "0.7"

//libraryDependencies +=  "io.reactivex" % "rxscala_2.11" % "0.26.2"


libraryDependencies += "com.netflix.rxjava" % "rxjava-scala" % "0.20.7"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.5"

libraryDependencies += "com.github.scala-blitz" %% "scala-blitz" % "1.1"

libraryDependencies += "com.storm-enroute" %% "scalameter-core" % "0.7"

// libraryDependencies += "com.twitter" %% "finagle-http" % "6.2.0" // only enable with 2.10.4

libraryDependencies += "org.scalaz" %% "scalaz-concurrent" % "7.2.4"

libraryDependencies += "com.typesafe.akka" %% "akka-stream-experimental" % "2.0.4"

libraryDependencies += "com.storm-enroute" %% "reactive-collections" % "0.5"





