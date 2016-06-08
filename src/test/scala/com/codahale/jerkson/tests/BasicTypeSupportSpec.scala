package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.JsonNode
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class BasicTypeSupportSpec extends FlatSpec with Matchers {

  behavior of "A Byte"
  it should "generates a JSON int" in {
    generate(15.toByte) shouldBe "15"
  }

  it should "is parsable from a JSON int" in {
    parse[Byte]("15") shouldBe 15
  }


  behavior of "A Short"
  it should "generates a JSON int" in {
    generate(15.toShort) shouldBe "15"
  }

  it should "is parsable from a JSON int" in {
    parse[Short]("15") shouldBe 15
  }


  behavior of "An Int"
  it should "generates a JSON int" in {
    generate(15) shouldBe "15"
  }

  it should "is parsable from a JSON int" in {
    parse[Int]("15") shouldBe 15
  }


  behavior of "A Long"
  it should "generates a JSON int" in {
    generate(15L) shouldBe "15"
  }

  it should "is parsable from a JSON int" in {
    parse[Long]("15") shouldBe 15L
  }


  behavior of "A BigInt"
  it should "generates a JSON int" in {
    generate(BigInt(15)) shouldBe "15"
  }

  it should "is parsable from a JSON int" in {
    parse[BigInt]("15") shouldBe BigInt(15)
  }

  it should "is parsable from a JSON string" in {
    parse[BigInt]("\"15\"") shouldBe BigInt(15)
  }


  behavior of "A Float"
  it should "generates a JSON float" in {
    generate(15.1F) shouldBe "15.1"
  }

  it should "is parsable from a JSON float" in {
    parse[Float]("15.1") shouldBe 15.1F
  }


  behavior of "A Double"
  it should "generates a JSON float" in {
    generate(15.1) shouldBe "15.1"
  }

  it should "is parsable from a JSON float" in {
    parse[Double]("15.1") shouldBe 15.1D
  }


  behavior of "A BigDecimal"
  it should "generates a JSON float" in {
    generate(BigDecimal(15.5)) shouldBe "15.5"
  }

  it should "is parsable from a JSON float" in {
    parse[BigDecimal]("15.5") shouldBe BigDecimal(15.5)
  }

  it should "is parsable from a JSON int" in {
    parse[BigDecimal]("15") shouldBe BigDecimal(15.0)
  }


  behavior of "A String"
  it should "generates a JSON string" in {
    generate("woo") shouldBe "\"woo\""
  }

  it should "is parsable from a JSON string" in {
    parse[String]("\"woo\"") shouldBe "woo"
  }


  behavior of "A StringBuilder"
  it should "generates a JSON string" in {
    generate(new StringBuilder("foo")) shouldBe "\"foo\""
  }

  it should "is parsable from a JSON string" in {
    parse[StringBuilder]("\"foo\"").toString() shouldBe "foo"
  }


  behavior of "A null Object"
  it should "generates a JSON null" in {
    generate[Object](null) shouldBe "null"
  }

  it should "is parsable from a JSON null" in {
    parse[Object]("null") shouldBe null
  }


  behavior of "A Boolean"
  it should "generates a JSON true" in {
    generate(true) shouldBe "true"
  }

  it should "generates a JSON false" in {
    generate(false) shouldBe "false"
  }

  it should "is parsable from a JSON true" in {
    parse[Boolean]("true") shouldBe true
  }

  it should "is parsable from a JSON false" in {
    parse[Boolean]("false") shouldBe false
  }


  behavior of "A Some[Int]"
  it should "generates a JSON int" in {
    generate(Some(12)) shouldBe "12"
  }

  it should "is parsable from a JSON int as an Option[Int]" in {
    parse[Option[Int]]("12") shouldBe Some(12)
  }


  behavior of "A None"
  it should "generates a JSON null" in {
    generate(None) shouldBe "null"
  }

  it should "is parsable from a JSON null as an Option[Int]" in {
    parse[Option[Int]]("null") shouldBe None
  }


  behavior of "A Left[String]"
  it should "generates a JSON string" in {
    generate(Left("woo")) shouldBe "\"woo\""
  }

  it should "is parsable from a JSON string as an Either[String, Int]" in {
    parse[Either[String, Int]]("\"woo\"") shouldBe Left("woo")
  }


  behavior of "A Right[String]"
  it should "generates a JSON string" in {
    generate(Right("woo")) shouldBe "\"woo\""
  }

  it should "is parsable from a JSON string as an Either[Int, String]" in {
    parse[Either[Int, String]]("\"woo\"") shouldBe Right("woo")
  }


  behavior of "A JsonNode"
  it should "generates whatever the JsonNode is" in {
    generate(new IntNode(2)) shouldBe "2"
  }

  it should "is parsable from a JSON AST node" in {
    parse[JsonNode]("2") shouldBe new IntNode(2)
  }

  it should "is parsable from a JSON AST node as a specific type" in {
    parse[IntNode]("2") shouldBe new IntNode(2)
  }

  it should "is itself parsable" in {
    parse[Int](new IntNode(2)) shouldBe 2
  }


  behavior of "An Array[Int]"
  it should "generates a JSON array of ints" in {
    generate(Array(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Array[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Array[Int]]("[]").toList shouldBe List.empty
  }
}
