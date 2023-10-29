package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.puzzler995.fedibean.activitypub.spec.modules.jackson.serializer.LinkSerializer;
import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
@JsonSerialize(using = LinkSerializer.class)
public class Link extends Resolvable {
  @Nonnull private CompactedIri href;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<String> type =
      new ArrayList<>() {
        {
          add("Link");
        }
      };

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<String> rel;

  private ContentType mediaType;
  private String name;
  private ContentMap nameMap;
  private Locale hreflang;
  private Integer height;
  private Integer width;
  private String summary;

  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
  private List<Resolvable> preview = new ArrayList<>();

  public Link(@JsonProperty("href") String href) {
    this.href = new CompactedIri(href);
  }

  public Link(@Nonnull CompactedIri href) {
    this.href = href;
  }

  @Override
  public CompactedIri getId() {
    return href;
  }

  @Override
  public void setId(CompactedIri id) {
    this.href = id;
  }

  @JsonGetter("mediaType")
  public String getMediaType() {
    return Objects.nonNull(mediaType) ? mediaType.getMimeType() : null;
  }

  @JsonSetter("mediaType")
  public Link setMediaType(String mediaType) {
    this.mediaType = ContentType.parse(mediaType);
    return this;
  }
}
