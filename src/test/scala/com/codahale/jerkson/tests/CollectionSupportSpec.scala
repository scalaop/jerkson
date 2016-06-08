package com.codahale.jerkson.tests

import scala.collection._
import com.codahale.jerkson.Json._
import org.scalatest.FlatSpec
import org.scalatest.Matchers

class CollectionSupportSpec extends FlatSpec with Matchers {

  behavior of "A collection.BitSet"
  it should "generates a JSON array of ints" in {
    generate(BitSet(1)) shouldBe "[1]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[BitSet]("[1,2,3]") shouldBe BitSet(1, 2, 3)
  }

  behavior of "A collection.Iterator[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3).iterator) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Iterator[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Iterator[Int]]("[]").toList shouldBe List.empty[Int]
  }

  behavior of "A collection.Traversable[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3).toTraversable) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Traversable[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Traversable[Int]]("[]").toList shouldBe List.empty[Int]
  }

  behavior of "A collection.BufferedIterator[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3).iterator.buffered) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[BufferedIterator[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[BufferedIterator[Int]]("[]").toList shouldBe List.empty[Int]
  }

  behavior of "A collection.Iterable[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3).toIterable) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Iterable[Int]]("[1,2,3]").toList shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Iterable[Int]]("[]").toList shouldBe List.empty[Int]
  }

  behavior of "A collection.Set[Int]"
  it should "generates a JSON array of ints" in {
    generate(Set(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Set[Int]]("[1,2,3]") shouldBe Set(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Set[Int]]("[]") shouldBe Set.empty[Int]
  }

  behavior of "A collection.Map[String, Int]"
  it should "generates a JSON object with int field values" in {
    generate(Map("one" -> 1, "two" -> 2)) shouldBe """{"one":1,"two":2}"""
  }

  it should "is parsable from a JSON object with int field values" in {
    parse[Map[String, Int]]("""{"one":1,"two":2}""") shouldBe Map("one" -> 1, "two" -> 2)
  }

  it should "is parsable from an empty JSON object" in {
    parse[Map[String, Int]]("{}") shouldBe Map.empty[String, Int]
  }

  behavior of "A collection.IndexedSeq[Int]"
  it should "generates a JSON array of ints" in {
    generate(IndexedSeq(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[IndexedSeq[Int]]("[1,2,3]") shouldBe IndexedSeq(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[IndexedSeq[Int]]("[]") shouldBe IndexedSeq.empty
  }

  behavior of "A collection.Seq[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Seq[Int]]("[1,2,3]") shouldBe Seq(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Seq[Int]]("[]") shouldBe Seq.empty[Int]
  }

  behavior of "A collection.SortedMap[String, Int]"
  it should "generates a JSON object with int field values" in {
    generate(SortedMap("one" -> 1, "two" -> 2)) shouldBe """{"one":1,"two":2}"""
  }

  // TODO: 6/1/11 <coda> -- figure out how to deserialize SortedMap instances

  /**
    * I think all this would take is a mapping from Class[_] to Ordering, which
    * would need to have hard-coded the various primitive types, and then add
    * support for Ordered and Comparable classes. Once we have the Ordering,
    * we can pass it in manually to a builder.
    */

  ignore should "is parsable from a JSON object with int field values" in {
    parse[SortedMap[String, Int]]("""{"one":1,"two":2}""") shouldBe SortedMap("one" -> 1, "two" -> 2)
  }

  ignore should "is parsable from an empty JSON object" in {
    parse[SortedMap[String, Int]]("{}") shouldBe SortedMap.empty[String, Int]
  }

  behavior of "A collection.SortedSet[Int]"
  it should "generates a JSON array of ints" in {
    generate(SortedSet(1, 2, 3)) shouldBe "[1,2,3]"
  }

  // TODO: 6/1/11 <coda> -- figure out how to deserialize SortedMap instances

  /**
    * I think all this would take is a mapping from Class[_] to Ordering, which
    * would need to have hard-coded the various primitive types, and then add
    * support for Ordered and Comparable classes. Once we have the Ordering,
    * we can pass it in manually to a builder.
    */

  ignore should "is parsable from a JSON array of ints" in {
    parse[SortedSet[Int]]("[1,2,3]") shouldBe SortedSet(1, 2, 3)

  }

  ignore should "is parsable from an empty JSON array" in {
    parse[SortedSet[Int]]("[]") shouldBe SortedSet.empty[Int]
  }
}
