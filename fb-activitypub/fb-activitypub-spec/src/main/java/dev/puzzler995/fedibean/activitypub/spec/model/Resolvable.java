package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.escalon.hypermedia.hydra.mapping.Vocab;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@Vocab("https://www.w3.org/ns/activitystreams#")
public class Resolvable {
  @JsonProperty("@id")
  private CompactedIri id;
  //@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  //private List<JsonContext> jsonContext = new ArrayList<>();
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<String> type;
  //@JsonAnySetter @JsonAnyGetter private Map<String, Object> unknownProperties = new HashMap<>();

  //public void addJsonContext(JsonContext context) {
  //  jsonContext.add(context);
  //}
  //
  //public void addJsonContext() {
  //  this.addJsonContext(JsonContext.activityStreams);
  //}
  //
  //@JsonProperty("@context")
  //public List<JsonContext> getJsonContext() {
  //  return jsonContext;
  //}
  //
  //@JsonProperty("@context")
  //@JsonDeserialize(using = JsonContextListDeserializer.class)
  //public void setJsonContext(List<JsonContext> jsonContext) {
  //  this.jsonContext = jsonContext;
  //}

  @JsonProperty("@type")
  public void setType(List<String> type) {
    this.type = type;
  }

  public void setType(String type) {
    this.type = new ArrayList<>();
    this.type.add(type);
  }
}
