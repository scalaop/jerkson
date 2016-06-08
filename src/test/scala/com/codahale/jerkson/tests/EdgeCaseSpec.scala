package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import java.io.ByteArrayInputStream
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class EdgeCaseSpec extends FlatSpec with Matchers {
  behavior of "Deserializing lists"
  it should "doesn't cache Seq builders" in {
    parse[List[Int]]("[1,2,3,4]") shouldBe List(1, 2, 3, 4)
    parse[List[Int]]("[1,2,3,4]") shouldBe List(1, 2, 3, 4)
  }

  behavior of "Parsing a JSON array of ints with nulls"
  it should "should be readable as a List[Option[Int]]" in {
    parse[List[Option[Int]]]("[1,2,null,4]") shouldBe List(Some(1), Some(2), None, Some(4))
  }

  behavior of "Deserializing maps"
  it should "doesn't cache Map builders" in {
    parse[Map[String, Int]](""" {"one":1, "two": 2} """) shouldBe Map("one" -> 1, "two" -> 2)
    parse[Map[String, Int]](""" {"one":1, "two": 2} """) shouldBe Map("one" -> 1, "two" -> 2)
  }

  behavior of "Parsing malformed JSON"
  it should "should throw a ParsingException with an informative message" in {
    the [ParsingException] thrownBy parse[Boolean]("jjf8;09") should have message
    "Malformed JSON. Unrecognized token 'jjf8': was expecting ('true', 'false' or 'null') at character offset 4."

    the [ParsingException] thrownBy parse[CaseClass]("{\"ye\":1") should have message
    "Malformed JSON. Unexpected end-of-input: expected close marker for " +
    "OBJECT at character offset 21."
  }

  behavior of "Parsing invalid JSON"
  it should "should throw a ParsingException with an informative message" in {
    val thrown = the [ParsingException] thrownBy parse[CaseClass]("900")
    thrown.getMessage should fullyMatch
      ("""Can not deserialize instance of com.codahale.jerkson.tests.CaseClass out of VALUE_NUMBER_INT token\n""" +
        """ at \[Source: java.io.StringReader@[0-9a-f]+; line: 1, column: 1\]""").r

    the [ParsingException] thrownBy parse[CaseClass]("{\"woo\": 1}") should have message "Invalid JSON. Needed [id, name], but found [woo]."
  }

  behavior of "Parsing an empty document"
  it should "should throw a ParsingException with an informative message" in {
    val input = new ByteArrayInputStream(Array.empty)
    val thrown = the [ParsingException] thrownBy parse[CaseClass](input)
    thrown.getMessage should startWith("No content to map due to end-of-input")
  }
}
