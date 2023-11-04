package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.deserializer.OffsetDateTimeDeserializer;
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

  private CompactedIri id;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> attachment = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> attributedTo = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> actor = new ArrayList<>(); // akkoma

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> audience = new ArrayList<>();

  private String content;
  private ContentMap contentMap;
  private APObject source;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> context = new ArrayList<>();

  private String name;
  private ContentMap nameMap;

  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime endTime;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> generator = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> icon = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> image = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> inReplyTo = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> location = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> preview = new ArrayList<>();

  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime published;

  private Collection replies;

  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime startTime;

  private String summary;
  private ContentMap summaryMap;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> tag = new ArrayList<>();

  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime updated;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> url = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> to = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> bto = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> cc = new ArrayList<>();

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> bcc = new ArrayList<>();

  private ContentType mediaType;
  private Duration duration;
  private String formerType;

  @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
  private OffsetDateTime deleted;

  private Integer width;
  private Integer height;

  private Boolean sensitive;
  private LdSignature signature;
  private String value;

  @JsonProperty("_misskey_content")
  private String misskeyContent;

  @JsonProperty("_misskey_quote")
  private CompactedIri misskeyQuote;

  private CompactedIri quoteUri;

  @JsonGetter("mediaType")
  public String getMediaType() {
    return Objects.nonNull(mediaType) ? mediaType.getMimeType() : null;
  }

  @JsonSetter("mediaType")
  public APObject setMediaType(String mediaType) {
    this.mediaType = ContentType.parse(mediaType);
    return this;
  }
}
