package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.OffsetDateTimeDeserializer;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer.OffsetDateTimeSerializer;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.http.entity.ContentType;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(value = {"atomUri", "conversation", "inReplyToAtomUri"})
public class APObject extends Resolvable {

  @JsonProperty("https://www.w3.org/ns/activitystreams#actor")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> actor = new ArrayList<>(); // akkoma
  @JsonProperty("https://www.w3.org/ns/activitystreams#attachment")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> attachment = new ArrayList<>();
@JsonProperty("https://www.w3.org/ns/activitystreams#attributedTo")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> attributedTo = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#audience")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> audience = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#bcc")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> bcc = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#bto")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> bto = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#cc")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> cc = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#content")
  private String content;
  @JsonProperty("https://www.w3.org/ns/activitystreams#contentMap")
  private ContentMap contentMap;
  @JsonProperty("https://www.w3.org/ns/activitystreams#context")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> context = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#deleted")
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime deleted;
  @JsonProperty("https://www.w3.org/ns/activitystreams#duration")
  private Duration duration;
  @JsonProperty("https://www.w3.org/ns/activitystreams#endTime")
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime endTime;
  @JsonProperty("https://www.w3.org/ns/activitystreams#formerType")
  private String formerType;
  @JsonProperty("https://www.w3.org/ns/activitystreams#generator")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> generator = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#height")
  private Integer height;
  @JsonProperty("https://www.w3.org/ns/activitystreams#icon")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> icon = new ArrayList<>();

  //private CompactedIri id;
  @JsonProperty("https://www.w3.org/ns/activitystreams#image")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> image = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#inReplyTo")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> inReplyTo = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#location")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> location = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#mediaType")
  private ContentType mediaType;
  @JsonProperty("https://misskey-hub.net/ns#_misskey_content")
  private String misskeyContent;
  @JsonProperty("https://misskey-hub.net/ns#_misskey_quote")
  private CompactedIri misskeyQuote;
  @JsonProperty("https://www.w3.org/ns/activitystreams#name")
  private String name;
  @JsonProperty("https://www.w3.org/ns/activitystreams#nameMap")
  private ContentMap nameMap;
  @JsonProperty("https://www.w3.org/ns/activitystreams#preview")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> preview = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#published")
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime published;
  @JsonProperty("https://misskey-hub.net/ns#quoteUri")
  private CompactedIri quoteUri;
  @JsonProperty("https://www.w3.org/ns/activitystreams#replies")
  private Collection replies;
  @JsonProperty("https://www.w3.org/ns/activitystreams#sensitive")
  private Boolean sensitive;
  @JsonProperty("_:signature")
  private LdSignature signature;
@JsonProperty("https://www.w3.org/ns/activitystreams#source")
  private APObject source;
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  @JsonProperty("https://www.w3.org/ns/activitystreams#startTime")
  private OffsetDateTime startTime;
  @JsonProperty("https://www.w3.org/ns/activitystreams#summary")
  private String summary;
  @JsonProperty("https://www.w3.org/ns/activitystreams#summaryMap")
  private ContentMap summaryMap;
  @JsonProperty("https://www.w3.org/ns/activitystreams#tag")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> tag = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#target")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> to = new ArrayList<>();
  @JsonProperty("https://www.w3.org/ns/activitystreams#updated")
  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  private OffsetDateTime updated;
  @JsonProperty("https://www.w3.org/ns/activitystreams#url")
  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> url = new ArrayList<>();
  @JsonProperty("http://schema.org#value")
  private String value;
  @JsonProperty("https://www.w3.org/ns/activitystreams#width")
  private Integer width;

  @JsonGetter("https://www.w3.org/ns/activitystreams#mediaType")
  public String getMediaType() {
    return Objects.nonNull(mediaType) ? mediaType.getMimeType() : null;
  }

  @JsonSetter("https://www.w3.org/ns/activitystreams#mediaType")
  public APObject setMediaType(String mediaType) {
    this.mediaType = ContentType.parse(mediaType);
    return this;
  }
}
