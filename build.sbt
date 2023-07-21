ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "untitled"
  )
val sparkVersion ="3.4.0"
val sparkDependencies = Seq{
  "org.apache.spark" %% "spark-core" % sparkVersion
  "org.apache.spark" %% "spark-sql" % sparkVersion
}
libraryDependencies ++=sparkDependencies