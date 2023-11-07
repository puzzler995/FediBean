package dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer;

import static dev.puzzler995.fedibean.activitypub.spec.model.constants.APConstants.CLASS_MAP;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.model.APObject;
import dev.puzzler995.fedibean.activitypub.spec.model.Link;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class ResolvableDeserializer extends StdDeserializer<Resolvable> {
  public ResolvableDeserializer() {
    this(Resolvable.class);
  }

  public ResolvableDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Resolvable deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    Resolvable value = null;
    JsonNode node = p.getCodec().readTree(p);
    if (!node.isObject()) {
      String href = node.asText();
      value = href != null ? new Link(href) : null;
    }
    if (node.has("@type")) {
      value = deserializeByType(node, p.getCodec());
    }
    if (value == null && node.has("href")) {
      value = p.getCodec().treeToValue(node, Link.class);
    }
    if (value == null) value = p.getCodec().treeToValue(node, APObject.class);
    return value;
  }

  private Resolvable deserializeByType(JsonNode node, ObjectCodec codec) throws IOException {
    JsonNode typeNode = node.get("@type");
    if (typeNode.isArray() || typeNode.isTextual()) {
      List<String> type = getTypeList(typeNode);
      Class<? extends Resolvable> resolvedClass =
          type.stream().map(CLASS_MAP::get).filter(Objects::nonNull).findFirst().orElse(null);
      if (resolvedClass != null) {
        return codec.treeToValue(node, resolvedClass);
      }
    }
    return null;
  }

  private List<String> getTypeList(JsonNode typeNode) {
    List<String> type = new ArrayList<>();
    if (typeNode.isArray()) {
      for (JsonNode typeEntry : typeNode) {
        String t = typeEntry.asText().substring(typeEntry.asText().lastIndexOf("#") + 1);
        if (t == null) {
          t = typeEntry.asText().substring(typeEntry.asText().lastIndexOf("/") + 1);
        }
        type.add(t);
      }
    } else if (typeNode.isTextual()) {
      String t = typeNode.asText().substring(typeNode.asText().lastIndexOf("#") + 1);
      if (t == null) {
        t = typeNode.asText().substring(typeNode.asText().lastIndexOf("/") + 1);
      }
      type.add(t);
    }
    return type;
  }
}
