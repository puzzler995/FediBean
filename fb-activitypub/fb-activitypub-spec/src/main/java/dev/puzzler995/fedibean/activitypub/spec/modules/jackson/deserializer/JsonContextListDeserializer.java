package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.model.JsonContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@JsonComponent
public class JsonContextListDeserializer extends StdDeserializer<List<JsonContext>> {
  public JsonContextListDeserializer() {
    this(null);
  }

  public JsonContextListDeserializer(Class<List<JsonContext>> vc) {
    super(vc);
  }

  @Override
  public List<JsonContext> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    List<JsonContext> contextList = new ArrayList<>();
    if (node.isTextual()) {
      //contextList.add(parseShortCode(null, node.asText(), Boolean.FALSE));
    } else if (node.isArray()) {
      Iterator<JsonNode> elements = node.elements();
      while (elements.hasNext()) {
        JsonNode element = elements.next();
        if (element.isTextual()) {
          //contextList.add(parseShortCode(null, element.asText(), Boolean.FALSE));
        } else if (element.isObject()) {
          contextList.add(new JsonContext(parseContextObject(element, Boolean.TRUE)));
        }
      }
    } else if (node.isObject()) {
      contextList.add(new JsonContext(parseContextObject(node, Boolean.TRUE)));
    }
    return contextList;
  }

  private List<JsonContext> parseContextObject(JsonNode node, boolean isChild) {
    List<JsonContext> contextList = new ArrayList<>();
    //Iterator<Entry<String, JsonNode>> fields = node.fields();
    //while (fields.hasNext()) {
    //  Entry<String, JsonNode> field = fields.next();
    //  if (field.getValue().isTextual()) {
    //    contextList.add(parseShortCode(field, isChild));
    //  } else if (field.getValue().isObject()) {
    //    contextList.add(
    //        new JsonContext(
    //            field.getKey(),
    //            null,
    //            null,
    //            parseContextObject(field.getValue(), isChild),
    //            isChild));
    //  }
    //}
    return contextList;
  }

  //private JsonContext parseShortCode(Entry<String, JsonNode> field, Boolean isChild) {
  //  return parseShortCode(field.getKey(), field.getValue().asText(), isChild);
  //}

  //private JsonContext parseShortCode(String field, String text, Boolean isChild) {
  //  Pattern pattern = Pattern.compile("(?<!http:|https:)(?!\\s):(?!\\/\\/)(?=\\S)");
  //  if (pattern.matcher(text).find()) {
  //    String[] split = text.split(":");
  //    return new JsonContext(field, split[0], split[1], null, isChild);
  //  } else {
  //    return new JsonContext(field, null, text, null, isChild);
  //  }
  //}
}
