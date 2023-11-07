package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.model.ContentMap;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

//@JsonComponent
public class ContentMapDeserializer extends StdDeserializer<ContentMap> {
  public ContentMapDeserializer() {
    this(ContentMap.class);
  }

  public ContentMapDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public ContentMap deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    Iterator<Entry<String, JsonNode>> fields = node.fields();
    ContentMap contentMap = new ContentMap();
    while (fields.hasNext()) {
      Entry<String, JsonNode> field = fields.next();
      contentMap.add(field.getKey(), field.getValue().asText());
    }
    return contentMap;
  }
}
