# Jerkson #

This fork of Jerkson patched to work with Scala 2.11 and 2.12

## Install ##

- This version of Jerkson is hosted on
  [Maven Central](http://central.maven.org/TBD).
- From sbt:

  ```scala
  libraryDependencies += "org.scalaop %% "jerkson" % "0.6.10"
  ```
- From Maven:

  ```xml
  <dependency>
    <groupId>org.scalaop</groupId>
    <artifactId>jerkson_2.12</artifactId>
    <version>0.6.10</version>
  </dependency>
  ```

## Build ##

```sh
$ cd jerkson
$ sbt compile
$ sbt test
```

## Future plans ##

Jerkson is legacy.
We won't do any further work except providing the 2.10 jars.
We suggest you switch to one of the many excellent Scala Json libraries.

## Contact ##

[opensource@cloudphysics.com](opensource@cloudphysics.com)
