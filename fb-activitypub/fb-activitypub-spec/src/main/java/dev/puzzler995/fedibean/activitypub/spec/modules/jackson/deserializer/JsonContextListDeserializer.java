package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.model.JsonContext;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

@JsonComponent
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
      contextList.add(new JsonContext(node.asText()));
    } else if (node.isArray()) {
      Iterator<JsonNode> elements = node.elements();
      while (elements.hasNext()) {
        JsonNode element = elements.next();
        if (element.isTextual()) {
          contextList.add(new JsonContext(element.asText()));
        } else if (element.isObject()) {
          contextList.addAll(parseContextObject(element));
        }
      }
    } else if (node.isObject()) {
      contextList.addAll(parseContextObject(node));
    }
    return contextList;
  }

  private List<JsonContext> parseContextObject(JsonNode node) {
    List<JsonContext> contextList = new ArrayList<>();
    Iterator<Entry<String, JsonNode>> fields = node.fields();
    while (fields.hasNext()) {
      Entry<String, JsonNode> field = fields.next();
      if (field.getValue().isTextual()) {
        if (field.getKey() != null) {
          contextList.add(new JsonContext(field.getKey(), field.getValue().asText()));
        } else {
          contextList.add(new JsonContext(field.getValue().asText()));
        }
      } else if (field.getValue().isObject()) {
        contextList.addAll(parseContextObject(field.getValue()));
      }
    }
    return contextList;
  }
}
