package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.JsonContextListDeserializer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Resolvable {
  private CompactedIri id;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<String> type;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<JsonContext> jsonContext = new ArrayList<>();

  @JsonAnySetter @JsonAnyGetter private Map<String, Object> unknownProperties = new HashMap<>();

  @JsonProperty("@context")
  public List<JsonContext> getJsonContext() {
    return jsonContext;
  }

  @JsonProperty("@context")
  @JsonDeserialize(using = JsonContextListDeserializer.class)
  public void setJsonContext(List<JsonContext> jsonContext) {
    this.jsonContext = jsonContext;
  }

  public void addJsonContext(JsonContext context) {
    jsonContext.add(context);
  }

  public void addJsonContext() {
    this.addJsonContext(JsonContext.activityStreams);
  }

  @JsonProperty("type")
  public void setType(List<String> type) {
    this.type = type;
  }

  public void setType(String type) {
    this.type = new ArrayList<>();
    this.type.add(type);
  }
}
