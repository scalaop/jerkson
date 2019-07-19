/* basic project info */
name := "jerkson"

organization := "org.scalaop"

licenses := Seq(
  ("The MIT License", url("http://codahale.com/mit.txt"))
)

homepage := Some(url("https://github.com/scalaop/jerkson"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/scalaop/jerkson"),
    "scm:git:https://github.com/scalaop/jerkson.git",
    Some("scm:git:git@github.com:scalaop/jerkson.git")
  )
)

/* scala versions and options */
scalaVersion := "2.12.8"

crossScalaVersions := Seq("2.12.8", "2.11.12")

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-unchecked",
  "-encoding", "UTF-8",
  "-optimise"
)


javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

/* dependencies */
libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % "2.7.4",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.4",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

libraryDependencies <+= scalaVersion {
  "org.scala-lang" % "scala-reflect" % _
}


/* testing */
parallelExecution in Test := true

/* sbt behavior */
logLevel in compile := Level.Warn

traceLevel := 5

offline := false

/* releasing */
releaseCrossBuild := true

releasePublishArtifactsAction := PgpKeys.publishSigned.value

/* publishing */
publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <developers>
    <developer>
      <id>cphylabs</id>
      <name>CloudPhysics Inc</name>
      <email>opensource@cloudphysics.com</email>
    </developer>
    <developer>
      <id>ebowman</id>
      <name>Eric Bowman</name>
      <url>https://github.com/ebowman</url>
    </developer>
    <developer>
      <id>gheine</id>
      <name>Gregor Heine</name>
      <url>https://github.com/gheine</url>
    </developer>
  </developers>
)
