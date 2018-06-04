name := "akkapersistence-example"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence" % "2.5.12",
  "com.typesafe.akka" %% "akka-cluster" % "2.5.12",
  "com.typesafe.akka" %% "akka-cluster-sharding" % "2.5.12",
  "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.84",
  "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % "0.84" % Test
)

assemblyOutputPath in assembly := baseDirectory.value / "jars/" / (assemblyJarName in assembly).value
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}