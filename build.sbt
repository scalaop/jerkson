/* basic project info */
name := "jerkson"

organization := "com.gilt"

licenses := Seq(
  ("The MIT License", url("http://codahale.com/mit.txt"))
)

homepage := Some(url("https://github.com/cphylabs/jerkson"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/cphylabs/jerkson"),
    "scm:git:https://github.com/cphylabs/jerkson.git",
    Some("scm:git:git@github.com:cphylabs/jerkson.git")
  )
)

/* scala versions and options */
scalaVersion := "2.10.5"

crossScalaVersions := Seq("2.10.5", "2.11.7")

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  // "-deprecation",
  "-unchecked",
  "-encoding", "UTF-8",
  "-optimise"
)

scalacOptions ++= Seq(
  "-Yclosure-elim",
  "-Yinline"
)

scalacOptions += "-target:jvm-1.6"

// These language flags will be used only for 2.10.x.
// Uncomment those you need, or if you hate SIP-18, all of them.
scalacOptions <++= scalaVersion map { sv =>
  if (sv startsWith "2.10") List(
    // "-Xverify",
    // "-Ywarn-all",
    "-feature",
    "-language:postfixOps",
    "-language:reflectiveCalls",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-language:existentials"
  )
  else Nil
}

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

/* dependencies */
libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % "2.7.4",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.4",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test"
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
  </developers>
)
