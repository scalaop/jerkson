package com.codahale.jerkson.tests

import java.net.URI
import com.codahale.jerkson.Json._
import java.util.UUID
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class FancyTypeSupportSpec extends FlatSpec with Matchers {
  behavior of "A URI"
  it should "generates a JSON string" in {
    generate(new URI("http://example.com/resource?query=yes")) shouldBe "\"http://example.com/resource?query=yes\""
  }

  it should "is parsable from a JSON string" in {
    parse[URI]("\"http://example.com/resource?query=yes\"") shouldBe new URI("http://example.com/resource?query=yes")
  }


  behavior of "A UUID"
  val uuid = UUID.fromString("a62047e4-bfb5-4d71-aad7-1a6b338eee63")

  it should "generates a JSON string" in {
    generate(uuid) shouldBe "\"a62047e4-bfb5-4d71-aad7-1a6b338eee63\""
  }

  it should "is parsable from a JSON string" in {
    parse[UUID]("\"a62047e4-bfb5-4d71-aad7-1a6b338eee63\"") shouldBe uuid
  }
}
