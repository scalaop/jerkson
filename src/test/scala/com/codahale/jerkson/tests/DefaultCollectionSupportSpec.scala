package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class DefaultCollectionSupportSpec extends FlatSpec with Matchers {
  behavior of "A Range"
  it should "generates a JSON object" in {
    generate(Range.inclusive(1, 4, 3)) shouldBe """{"start":1,"end":4,"step":3,"inclusive":true}"""
  }

  it should "generates a JSON object without the inclusive field if it's exclusive" in {
    generate(Range(1, 4, 3)) shouldBe """{"start":1,"end":4,"step":3}"""
  }

  it should "generates a JSON object without the step field if it's 1" in {
    generate(Range(1, 4)) shouldBe """{"start":1,"end":4}"""
  }

  it should "is parsable from a JSON object" in {
    parse[Range]("""{"start":1,"end":4,"step":3,"inclusive":true}""") shouldBe Range.inclusive(1, 4, 3)
  }

  it should "is parsable from a JSON object without the inclusive field" in {
    parse[Range]("""{"start":1,"end":4,"step":3}""") shouldBe Range(1, 4, 3)
  }

  it should "is parsable from a JSON object without the step field" in {
    parse[Range]("""{"start":1,"end":4}""") shouldBe Range(1, 4)
  }

  it should "is not parsable from a JSON object without the required fields" in {
    the [ParsingException] thrownBy parse[Range]("""{"start":1}""") should have message "Invalid JSON. Needed [start, end, <step>, <inclusive>], but found [start]."
  }


  behavior of "A Pair[Int]"
  ignore should "generates a two-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Pair serialization
    generate(Pair(1, 2)) shouldBe "[1,2]"
  }

  ignore should "is parsable from a two-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Pair deserialization
    parse[Pair[Int, Int]]("[1,2]") shouldBe Pair(1, 2)
  }

  behavior of "A Triple[Int]"
  ignore should "generates a three-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Triple serialization
    generate(Triple(1, 2, 3)) shouldBe "[1,2,3]"
  }

  ignore should "is parsable from a three-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Triple deserialization
    parse[Triple[Int, Int, Int]]("[1,2,3]") shouldBe Triple(1, 2, 3)
  }

  behavior of "A four-tuple"
  ignore should "generates a four-element JSON array" in {
    // TODO: 5/31/11 <coda> -- fix Tuple4 serialization
    generate((1, "2", 3, "4")) shouldBe "[1,\"2\",3,\"4\"]"
  }

  ignore should "is parsable from a three-element JSON array of ints" in {
    // TODO: 5/31/11 <coda> -- fix Tuple4 deserialization
    parse[(Int, String, Int, String)]("[1,\"2\",3,\"4\"]") shouldBe (1, "2", 3, "4")
  }

  // TODO: 6/1/11 <coda> -- add support for all Tuple1->TupleBillionty types

  behavior of "A Seq[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Seq[Int]]("[1,2,3]") shouldBe Seq(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Seq[Int]]("[]") shouldBe Seq.empty[Int]
  }

  behavior of "A List[Int]"
  it should "generates a JSON array of ints" in {
    generate(List(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[List[Int]]("[1,2,3]") shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[List[Int]]("[]") shouldBe List.empty[Int]
  }

  behavior of "An IndexedSeq[Int]"
  it should "generates a JSON array of ints" in {
    generate(IndexedSeq(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[IndexedSeq[Int]]("[1,2,3]") shouldBe IndexedSeq(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[IndexedSeq[Int]]("[]") shouldBe IndexedSeq.empty[Int]
  }

  behavior of "A Vector[Int]"
  it should "generates a JSON array of ints" in {
    generate(Vector(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Vector[Int]]("[1,2,3]") shouldBe Vector(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Vector[Int]]("[]") shouldBe Vector.empty[Int]
  }

  behavior of "A Set[Int]"
  it should "generates a JSON array of ints" in {
    generate(Set(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Set[Int]]("[1,2,3]") shouldBe Set(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Set[Int]]("[]") shouldBe Set.empty[Int]
  }

  behavior of "A Map[String, Int]"
  it should "generates a JSON object with int field values" in {
    generate(Map("one" -> 1, "two" -> 2)) shouldBe """{"one":1,"two":2}"""
  }

  it should "is parsable from a JSON object with int field values" in {
    parse[Map[String, Int]]("""{"one":1,"two":2}""") shouldBe Map("one" -> 1, "two" -> 2)
  }

  it should "is parsable from an empty JSON object" in {
    parse[Map[String, Int]]("{}") shouldBe Map.empty[String, Int]
  }

  behavior of "A Map[String, Any]"
  it should "generates a JSON object with mixed field values" in {
    generate(Map("one" -> 1, "two" -> "2")) shouldBe """{"one":1,"two":"2"}"""
  }

  it should "is parsable from a JSON object with mixed field values" in {
    parse[Map[String, Any]]("""{"one":1,"two":"2"}""") shouldBe Map[String, Any]("one" -> 1, "two" -> "2")
  }

  it should "is parsable from an empty JSON object" in {
    parse[Map[String, Any]]("{}") shouldBe Map.empty[String, Any]
  }

  behavior of "A Stream[Int]"
  it should "generates a JSON array" in {
    generate(Stream(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Stream[Int]]("[1,2,3]") shouldBe Stream(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Stream[Int]]("[]") shouldBe Stream.empty[Int]
  }

  behavior of "An Iterator[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3).iterator) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Iterator[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Iterator[Int]]("[]").toList shouldBe List.empty[Int]
  }

  behavior of "A Traversable[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3).toTraversable) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Traversable[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Traversable[Int]]("[]").toList shouldBe List.empty[Int]
  }

  behavior of "A BufferedIterator[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3).iterator.buffered) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[BufferedIterator[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[BufferedIterator[Int]]("[]").toList shouldBe List.empty[Int]
  }

  behavior of "An Iterable[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3).toIterable) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Iterable[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Iterable[Int]]("[]").toList shouldBe List.empty[Int]
  }
}
