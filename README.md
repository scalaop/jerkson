# Jerkson #

This fork of Jerkson contains patches not found in the original, abandoned, copy.

## Differences from upstream Jerkson ##

- sbt instead of Maven.
- Tests have been converted to scalatest
  [simplespec](https://github.com/SimpleFinance/simplespec) tests.
- Minor tweaks to get compilation in 2.10.
- [Streaming iteration patch](https://github.com/ymasory/jerkson/pull/1), if you
  use version 0.6.2+.
- [Streaming field iterator patch](https://github.com/cphylabs/jerkson/commit/85076b9daeeb3ba3c3c3f0fbdd60deaf09fb275b), if you use version 0.6.3+.

## Install ##

- This version of Jerkson is hosted on
  [Maven Central](http://central.maven.org/maven2/com/cloudphysics/jerkson_2.10).
- From sbt:

  ```scala
  libraryDependencies += "com.gilt %% "jerkson" % "0.6.8-2"
  ```
- From Maven:

  ```xml
  <dependency>
    <groupId>com.gilt</groupId>
    <artifactId>jerkson_2.10</artifactId>
    <version>0.6.8-2</version>
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
