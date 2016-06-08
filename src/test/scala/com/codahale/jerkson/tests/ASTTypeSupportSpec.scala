package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.AST._
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ASTTypeSupportSpec extends FlatSpec with Matchers {

  behavior of "An AST.JInt"

  it should "generate a JSON int" in {
    generate(JInt(15)) should be("15")
  }

  it should "is parsable from a JSON int" in {
    parse[JInt]("15") shouldBe JInt(15)
  }

  it should "is parsable from a JSON int as a JValue" in {
    parse[JValue]("15") shouldBe JInt(15)
  }


  behavior of "An AST.JFloat"

  it should "generate a JSON int" in {
    generate(JFloat(15.1)) shouldBe "15.1"
  }

  it should "is parsable from a JSON float" in {
    parse[JFloat]("15.1") shouldBe JFloat(15.1)
  }

  it should "is parsable from a JSON float as a JValue" in {
    parse[JValue]("15.1") shouldBe JFloat(15.1)
  }


  behavior of "An AST.JString"

  it should "generate a JSON string" in {
    generate(JString("woo")) shouldBe "\"woo\""
  }

  it should "is parsable from a JSON string" in {
    parse[JString]("\"woo\"") shouldBe JString("woo")
  }

  it should "is parsable from a JSON string as a JValue" in {
    parse[JValue]("\"woo\"") shouldBe JString("woo")
  }


  behavior of "An AST.JNull"
  it should "generates a JSON null" in {
    generate(JNull) shouldBe "null"
  }

  it should "is parsable from a JSON null" in {
    parse[JNull.type]("null") shouldBe JNull
  }

  it should "is parsable from a JSON null as a JValue" in {
    parse[JValue]("null") shouldBe JNull
  }


  behavior of "An AST.JBoolean"
  it should "generates a JSON true" in {
    generate(JBoolean(true)) shouldBe "true"
  }

  it should "generates a JSON false" in {
    generate(JBoolean(false)) shouldBe "false"
  }

  it should "is parsable from a JSON true" in {
    parse[JBoolean]("true") shouldBe JBoolean(true)
  }

  it should "is parsable from a JSON false" in {
    parse[JBoolean]("false") shouldBe JBoolean(false)
  }

  it should "is parsable from a JSON true as a JValue" in {
    parse[JValue]("true") shouldBe JBoolean(true)
  }

  it should "is parsable from a JSON false as a JValue" in {
    parse[JValue]("false") shouldBe JBoolean(false)
  }


  behavior of "An AST.JArray of JInts"
  it should "generates a JSON array of ints" in {
    generate(JArray(List(JInt(1), JInt(2), JInt(3)))) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[JArray]("[1,2,3]") shouldBe JArray(List(JInt(1), JInt(2), JInt(3)))
  }

  it should "is parsable from a JSON array of ints as a JValue" in {
    parse[JValue]("[1,2,3]") shouldBe JArray(List(JInt(1), JInt(2), JInt(3)))
  }


  behavior of "An AST.JObject"
  val obj = JObject(List(JField("id", JInt(1)), JField("name", JString("Coda"))))

  it should "generates a JSON object with matching field values" in {
    generate(obj) shouldBe """{"id":1,"name":"Coda"}"""
  }

  it should "is parsable from a JSON object" in {
    parse[JObject]("""{"id":1,"name":"Coda"}""") shouldBe obj
  }

  it should "is parsable from a JSON object as a JValue" in {
    parse[JValue]("""{"id":1,"name":"Coda"}""") shouldBe obj
  }

  it should "is parsable from an empty JSON object" in {
    parse[JObject]("""{}""") shouldBe JObject(Nil)
  }
}
