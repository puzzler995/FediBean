package dev.puzzler995.fedibean.federation.spec.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonContext {
  public static final JsonContext ACTIVITY_STREAMS =
      new JsonContext(null, null, "https://www.w3.org/ns/activitystreams");
  private String iri;
  private String key;
  private String prefix;

  public static JsonContext fromString(String contextString) {
    JsonContext context;
    if (contextString.startsWith("http")) {
      context = new JsonContext(null, null, contextString);
    } else {
      String[] split = contextString.split(":(?![^:]*//)", 2);
      if (split.length == 2) {
        if (split[1].startsWith("http")) {
          context = new JsonContext(split[0], null, split[1]);
        } else {
          String[] iriParts = split[1].split(":");
          if (iriParts.length == 2) {
            context = new JsonContext(split[0], iriParts[0], iriParts[1]);
          } else {
            throw new IllegalArgumentException("Invalid context string: " + contextString);
          }
        }
      } else {
        throw new IllegalArgumentException("Invalid context string: " + contextString);
      }
    }
    return context;
  }

  public static JsonContext fromString(String key, String contextString) {
    JsonContext context;
    if (contextString.startsWith("http")) {
      context = new JsonContext(key, null, contextString);
    } else {
      String[] split = contextString.split(":(?![^:]*//)", 2);
      if (split.length == 2) {
        context = new JsonContext(key, split[0], split[1]);
      } else {
        throw new IllegalArgumentException("Invalid context string: " + contextString);
      }
    }

    return context;
  }

  @JsonGetter
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (key != null) {
      builder.append("\"");
      builder.append(key);
      builder.append("\": ");
    }
    builder.append("\"");
    if (prefix != null) {
      builder.append(prefix);
      builder.append(":");
    }
    builder.append(iri);
    builder.append("\"");
    return builder.toString();
  }
}
