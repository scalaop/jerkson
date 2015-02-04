package com.codahale.jerkson.deser

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.node.TreeTraversingParser
import com.fasterxml.jackson.databind._

class EitherDeserializer(config: DeserializationConfig,
                         javaType: JavaType) extends JsonDeserializer[Object] {
  def deserialize(jp: JsonParser, ctxt: DeserializationContext) = {
    val node = jp.readValueAsTree[JsonNode]

    try {
      val tp = new TreeTraversingParser(node, jp.getCodec)
      Left(tp.getCodec.readValue[Object](tp, javaType.containedType(0)))
    } catch {
      case _: Throwable => {
        val tp = new TreeTraversingParser(node, jp.getCodec)
        Right(tp.getCodec.readValue[Object](tp, javaType.containedType(1)))
      }
    }
  }

  override def isCachable = true
}
