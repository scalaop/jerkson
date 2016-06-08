package com.codahale.jerkson.tests

import com.codahale.jerkson.Json._
import com.codahale.jerkson.ParsingException
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import scala.collection.mutable._

class MutableCollectionSupportSpec extends FlatSpec with Matchers {
  behavior of "A mutable.ResizableArray[Int]"
  it should "generates a JSON array of ints" in {
    generate(ResizableArray(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[ResizableArray[Int]]("[1,2,3]") shouldBe ResizableArray(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[ResizableArray[Int]]("[]") shouldBe ResizableArray.empty[Int]
  }

  behavior of "A mutable.ArraySeq[Int]"
  it should "generates a JSON array of ints" in {
    generate(ArraySeq(1, 2, 3)) shouldBe "[1,2,3]"
  }


  it should "is parsable from a JSON array of ints" in {
    parse[ArraySeq[Int]]("[1,2,3]") shouldBe ArraySeq(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[ArraySeq[Int]]("[]") shouldBe ArraySeq.empty[Int]
  }

  behavior of "A mutable.MutableList[Int]"
  private val xs = new MutableList[Int]
  xs ++= List(1, 2, 3)

  it should "generates a JSON array" in {
    generate(xs) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[MutableList[Int]]("[1,2,3]") shouldBe xs
  }

  it should "is parsable from an empty JSON array" in {
    parse[MutableList[Int]]("[]") shouldBe new MutableList[Int]()
  }

  behavior of "A mutable.Queue[Int]"
  it should "generates a JSON array" in {
    generate(Queue(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[Queue[Int]]("[1,2,3]") shouldBe Queue(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[Queue[Int]]("[]") shouldBe new Queue[Int]()
  }

  behavior of "A mutable.ListBuffer[Int]"
  it should "generates a JSON array" in {
    generate(ListBuffer(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[ListBuffer[Int]]("[1,2,3]") shouldBe ListBuffer(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[ListBuffer[Int]]("[]") shouldBe ListBuffer.empty[Int]
  }

  behavior of "A mutable.ArrayBuffer[Int]"
  it should "generates a JSON array" in {
    generate(ArrayBuffer(1, 2, 3)) shouldBe "[1,2,3]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[ArrayBuffer[Int]]("[1,2,3]") shouldBe ArrayBuffer(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[ArrayBuffer[Int]]("[]") shouldBe ArrayBuffer.empty[Int]
  }

  behavior of "A mutable.BitSet"
  it should "generates a JSON array" in {
    generate(BitSet(1)) shouldBe "[1]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[BitSet]("[1,2,3]") shouldBe BitSet(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[BitSet]("[]") shouldBe BitSet.empty
  }

  behavior of "A mutable.HashSet[Int]"
  it should "generates a JSON array" in {
    generate(HashSet(1)) shouldBe "[1]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[HashSet[Int]]("[1,2,3]") shouldBe HashSet(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[HashSet[Int]]("[]") shouldBe HashSet.empty[Int]
  }

  behavior of "A mutable.LinkedHashSet[Int]"
  it should "generates a JSON array" in {
    generate(LinkedHashSet(1)) shouldBe "[1]"
  }

  it should "is parsable from a JSON array of ints" in {
    parse[LinkedHashSet[Int]]("[1,2,3]") shouldBe LinkedHashSet(1, 2, 3)
  }

  it should "is parsable from an empty JSON array" in {
    parse[LinkedHashSet[Int]]("[]") shouldBe LinkedHashSet.empty[Int]
  }

  behavior of "A mutable.Map[String, Int]"
  it should "generates a JSON object" in {
    generate(Map("one" -> 1)) shouldBe """{"one":1}"""
  }

  it should "is parsable from a JSON object with int field values" in {
    parse[Map[String, Int]]("""{"one":1}""") shouldBe Map("one" -> 1)
  }

  it should "is parsable from an empty JSON object" in {
    parse[Map[String, Int]]("{}") shouldBe Map.empty[String, Int]
  }

  behavior of "A mutable.Map[String, Any]"
  it should "is not parsable from an empty JSON object in a JSON array" in {
    a [ParsingException] should be thrownBy parse[Map[String, Any]]("[{}]")
  }

  behavior of "A mutable.HashMap[String, Int]"
  it should "generates a JSON object" in {
    generate(HashMap("one" -> 1)) shouldBe """{"one":1}"""
  }

  it should "is parsable from a JSON object with int field values" in {
    parse[HashMap[String, Int]]("""{"one":1}""") shouldBe HashMap("one" -> 1)
  }

  it should "is parsable from an empty JSON object" in {
    parse[HashMap[String, Int]]("{}") shouldBe HashMap.empty[String, Int]
  }

  behavior of "A mutable.LinkedHashMap[String, Int]"
  it should "generates a JSON object" in {
    generate(LinkedHashMap("one" -> 1)) shouldBe """{"one":1}"""
  }

  it should "is parsable from a JSON object with int field values" in {
    parse[LinkedHashMap[String, Int]]("""{"one":1}""") shouldBe LinkedHashMap("one" -> 1)
  }

  it should "is parsable from an empty JSON object" in {
    parse[LinkedHashMap[String, Int]]("{}") shouldBe LinkedHashMap.empty[String, Int]
  }
}
