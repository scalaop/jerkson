package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.collection.immutable._

class ImmutableCollectionSupportSpec extends FlatSpec with Matchers {
  behavior of "An immutable.Seq[Int]"
  it should "generates a JSON array of ints" in {
    generate(Seq(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Seq[Int]]("[1,2,3]") shouldBe Seq(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Seq[Int]]("[]") shouldBe Seq.empty[Int]
  }

  behavior of "An immutable.List[Int]"
  it should "generates a JSON array of ints" in {
    generate(List(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[List[Int]]("[1,2,3]") shouldBe List(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[List[Int]]("[]") shouldBe List.empty[Int]
  }

  behavior of "An immutable.IndexedSeq[Int]"
  it should "generates a JSON array of ints" in {
    generate(IndexedSeq(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[IndexedSeq[Int]]("[1,2,3]") shouldBe IndexedSeq(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[IndexedSeq[Int]]("[]") shouldBe IndexedSeq.empty[Int]
  }

  behavior of "An immutable.TreeSet[Int]"
  it should "generates a JSON array" in {
    generate(TreeSet(1)) shouldBe "[1]"
  }

  // TODO: 6/1/11 <coda> -- figure out how to deserialize TreeSet instances

  /**
    * I think all this would take is a mapping from Class[_] to Ordering, which
    * would need to have hard-coded the various primitive types, and then add
    * support for Ordered and Comparable classes. Once we have the Ordering,
    * we can pass it in manually to a builder.
    */
  
  ignore should "is parsable from a JSON array of ints" in {
    parse[TreeSet[Int]]("[1,2,3]") shouldBe TreeSet(1, 2, 3)
  }

  ignore should "is parsable from an empty JSON array" in {
    parse[TreeSet[Int]]("[]") shouldBe TreeSet.empty[Int]
  }

  behavior of "An immutable.HashSet[Int]"
  it should "generates a JSON array" in {
    generate(HashSet(1)) shouldBe "[1]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[HashSet[Int]]("[1,2,3]") shouldBe HashSet(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[HashSet[Int]]("[]") shouldBe HashSet.empty[Int]
  }

  behavior of "An immutable.BitSet"
  it should "generates a JSON array" in {
    generate(BitSet(1)) shouldBe "[1]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[BitSet]("[1,2,3]") shouldBe BitSet(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[BitSet]("[]") shouldBe BitSet.empty
  }

  behavior of "An immutable.TreeMap[String, Int]"
  it should "generates a JSON object" in {
    generate(TreeMap("one" -> 1)) shouldBe """{"one":1}"""
  }

  // TODO: 6/1/11 <coda> -- figure out how to deserialize TreeMap instances

  /**
    * I think all this would take is a mapping from Class[_] to Ordering, which
    * would need to have hard-coded the various primitive types, and then add
    * support for Ordered and Comparable classes. Once we have the Ordering,
    * we can pass it in manually to a builder.
    */
  
  ignore should "is parsable from a JSON object with int field values" in {
    parse[TreeMap[String, Int]]("""{"one":1}""") shouldBe TreeMap("one" -> 1)
  }

  ignore should "is parsable from an empty JSON object" in {
    parse[TreeMap[String, Int]]("{}") shouldBe TreeMap.empty[String, Int]
  }

  behavior of "An immutable.HashMap[String, Int]"
  it should "generates a JSON object" in {
    generate(HashMap("one" -> 1)) shouldBe """{"one":1}"""
  }

  it should "is parsable from a JSON object with int field values" in {
    parse[HashMap[String, Int]]("""{"one":1}""") shouldBe HashMap("one" -> 1)
  }

  it should "is parsable from an empty JSON object" in {
    parse[HashMap[String, Int]]("{}") shouldBe HashMap.empty[String, Int]
  }

  behavior of "An immutable.HashMap[String, Any]"
  it should "generates a JSON object" in {
    generate(HashMap[String, Any]("one" -> 1)) shouldBe """{"one":1}"""
  }

  it should "is parsable from a JSON object with int field values" in {
    parse[HashMap[String, Any]]("""{"one":1}""") shouldBe HashMap("one" -> 1)
  }

  it should "is parsable from an empty JSON object" in {
    parse[HashMap[String, Any]]("{}") shouldBe HashMap.empty[String, Any]
  }

  it should "is not parsable from an empty JSON object in a JSON array" in {
    a [ParsingException] should be thrownBy parse[HashMap[String, Any]]("[{}]")
  }

  behavior of "An immutable.Map[Int, String]"
  it should "generates a JSON object" in {
    generate(Map(1 -> "one")) shouldBe """{"1":"one"}"""
  }

  it should "is parsable from a JSON object with decimal field names and string field values" in {
    parse[Map[Int, String]]("""{"1":"one"}""") shouldBe Map(1 -> "one")
  }

  it should "is not parsable from a JSON object with non-decimal field names" in {
    a [ParsingException] should be thrownBy parse[Map[Int, String]]("""{"one":"one"}""")
  }

  it should "is parsable from an empty JSON object" in {
    parse[Map[Int, String]]("{}") shouldBe Map.empty[Int, String]
  }

  behavior of "An immutable.Map[Int, Any]"
  it should "is not parsable from an empty JSON object in a JSON array" in {
    a [ParsingException] should be thrownBy parse[Map[Int, Any]]("[{}]")
  }

  behavior of "An immutable.IntMap[Any]"
  it should "is not parsable from an empty JSON object in a JSON array" in {
    a [ParsingException] should be thrownBy parse[IntMap[Any]]("[{}]")
  }

  behavior of "An immutable.LongMap[Any]"
  it should "is not parsable from an empty JSON object in a JSON array" in {
    a [ParsingException] should be thrownBy parse[LongMap[Any]]("[{}]")
  }

  behavior of "An immutable.Map[Long, Any]"
  it should "is not parsable from an empty JSON object in a JSON array" in {
    a [ParsingException] should be thrownBy parse[Map[Long, Any]]("[{}]")
  }

  behavior of "An immutable.Map[Long, String]"
  it should "generates a JSON object" in {
    generate(Map(1L -> "one")) shouldBe """{"1":"one"}"""
  }

  it should "is parsable from a JSON object with decimal field names and string field values" in {
    parse[Map[Long, String]]("""{"1":"one"}""") shouldBe Map(1L -> "one")
  }

  it should "is not parsable from a JSON object with non-decimal field names" in {
    a [ParsingException] should be thrownBy parse[Map[Long, String]]("""{"one":"one"}""")
  }

  it should "is parsable from an empty JSON object" in {
    parse[Map[Long, String]]("{}") shouldBe Map.empty[Long, String]
  }

  behavior of "An immutable.IntMap[String]"
  it should "generates a JSON object" in {
    generate(IntMap(1 -> "one")) shouldBe """{"1":"one"}"""
  }

  it should "is parsable from a JSON object with decimal field names and string field values" in {
    parse[IntMap[String]]("""{"1":"one"}""") shouldBe IntMap(1 -> "one")
  }

  it should "is not parsable from a JSON object with non-decimal field names" in {
    a [ParsingException] should be thrownBy parse[IntMap[String]]("""{"one":"one"}""")
  }

  it should "is parsable from an empty JSON object" in {
    parse[IntMap[String]]("{}") shouldBe IntMap.empty[String]
  }

  behavior of "An immutable.LongMap[String]"
  it should "generates a JSON object" in {
    generate(LongMap(1L -> "one")) shouldBe """{"1":"one"}"""
  }

  it should "is parsable from a JSON object with int field names and string field values" in {
    parse[LongMap[String]]("""{"1":"one"}""") shouldBe LongMap(1L -> "one")
  }

  it should "is not parsable from a JSON object with non-decimal field names" in {
    a [ParsingException] should be thrownBy parse[LongMap[String]]("""{"one":"one"}""")
  }

  it should "is parsable from an empty JSON object" in {
    parse[LongMap[String]]("{}") shouldBe LongMap.empty
  }

  behavior of "An immutable.Queue[Int]"
  it should "generates a JSON array" in {
    generate(Queue(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Queue[Int]]("[1,2,3]") shouldBe Queue(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Queue[Int]]("[]") shouldBe Queue.empty
  }
}
