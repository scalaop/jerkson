package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.AST._
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class JValueSpec extends FlatSpec with Matchers {
  behavior of "Selecting single nodes"
  it should "returns None with primitives" in {
    (parse[JValue]("8") \ "blah") shouldBe JNull
  }

  it should "returns None on nonexistent fields" in {
    (parse[JValue]("{\"one\": \"1\"}") \ "two") shouldBe JNull
  }

  it should "returns a JValue with an existing field" in {
    (parse[JValue]("{\"one\": \"1\"}") \ "one") shouldBe JString("1")
  }

  behavior of "Selecting array members"
  it should "returns None with primitives" in {
    (parse[JValue]("\"derp\"").apply(0)) shouldBe JNull
  }

  it should "returns None on out of bounds" in {
    (parse[JValue]("[0, 1, 2, 3]").apply(4)) shouldBe JNull
  }

  it should "returns a JValue" in {
    (parse[JValue]("[0, 1, 2, 3]").apply(2)) shouldBe JInt(2)
  }

  behavior of "Deep selecting"
  it should "returns Nil with primitives" in {
    (parse[JValue]("0.234") \\ "herp") shouldBe empty
  }

  it should "returns Nil on nothing found" in {
    (parse[JValue]("{\"one\": {\"two\" : \"three\"}}") \\ "four") shouldBe empty
  }

  it should "returns single leaf nodes" in {
    (parse[JValue]("{\"one\": {\"two\" : \"three\"}}") \\ "two") shouldBe Seq(JString("three"))
  }

  it should "should return multiple leaf nodes" in {
    (parse[JValue]("{\"one\": {\"two\" : \"three\"}, \"four\": {\"two\" : \"five\"}}") \\ "two") shouldBe Seq(JString("three"),JString("five"))
  }
}
