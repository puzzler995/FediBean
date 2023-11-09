package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.old.JsonContextSerializer;
import java.util.List;
import java.util.ListIterator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonSerialize(using = JsonContextSerializer.class)
@AllArgsConstructor
public class JsonContext {
  public static final JsonContext activityStreams =
      new JsonContext("https://www.w3.org/ns/activitystreams");
  private List<JsonContext> childContexts;
  private Boolean isChild = false;
  private String name;
  private String prefix;
  private String suffix;

  public JsonContext(List<JsonContext> childContexts) {
    this.childContexts = childContexts;
  }

  public JsonContext(String prefix, List<JsonContext> childContexts) {
    this(childContexts);
    this.prefix = prefix;
  }

  public JsonContext(String suffix) {
    this.suffix = suffix;
  }

  public JsonContext(String prefix, String suffix) {
    this(suffix);
    this.prefix = prefix;
  }

  @JsonGetter
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (childContexts == null || childContexts.isEmpty()) {
      if (name != null) {
        builder.append(name).append(": ");
      }
      if (prefix != null) {
        builder.append(prefix).append(":");
      }
      if (suffix != null) {
        builder.append(suffix);
      }
    } else {
      ListIterator<JsonContext> iterator = childContexts.listIterator();
      if (name != null) {
        builder.append(name).append(": {");
      } else {
        builder.append("{");
      }
      while (iterator.hasNext()) {
        builder.append(iterator.next().toString());
        if (iterator.hasNext()) builder.append(", ");
      }
      builder.append("}");
    }
    return builder.toString();
  }
}
