package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer.JsonContextSerializer;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonSerialize(using = JsonContextSerializer.class)
public class JsonContext {
  public static final JsonContext activityStreams =
      new JsonContext("https://www.w3.org/ns/activitystreams");
  private String prefix;
  private String suffix;

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
    return prefix == null ? suffix : prefix + ":" + suffix;
  }
}
