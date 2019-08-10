name := "ProcessStream"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "1.6.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kafka" % sparkVersion
)

libraryDependencies += "org.apache.kafka" %% "kafka" % "2.0.0"