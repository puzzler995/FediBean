package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = ExampleConstants.EMOJI_TABLE_NAME)
public class Emoji implements Serializable {
  private static final long serialVersionUID = 6670060516872292988L;
  @Column(name = ExampleConstants.EMOJI_COLUMN_ALIASES_NAME, nullable = false)
  private List<String> aliases;
  @Column(name = ExampleConstants.EMOJI_COLUMN_CATEGORY_NAME, length = 128)
  private String category;
  @Column(name = ExampleConstants.EMOJI_COLUMN_HEIGHT_NAME)
  private Integer height;
  @Column(name = ExampleConstants.EMOJI_COLUMN_HOST_NAME, length = 128)
  private String host;
  @Id
  @Column(name = ExampleConstants.EMOJI_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;
  @Column(name = ExampleConstants.EMOJI_COLUMN_LICENSE_NAME, length = 1024)
  private String license;
  @Column(name = ExampleConstants.EMOJI_COLUMN_NAME_NAME, nullable = false, length = 128)
  private String name;
  @Column(name = ExampleConstants.EMOJI_COLUMN_ORIGINALURL_NAME, nullable = false, length = 512)
  private String originalUrl;
  @Column(name = ExampleConstants.EMOJI_COLUMN_PUBLICURL_NAME, nullable = false, length = 512)
  private String publicUrl;
  @Column(name = ExampleConstants.EMOJI_COLUMN_TYPE_NAME, length = 64)
  private String type;
  @Column(name = ExampleConstants.EMOJI_COLUMN_UPDATEDAT_NAME)
  private OffsetDateTime updatedAt;
  @Column(name = ExampleConstants.EMOJI_COLUMN_URI_NAME, length = 512)
  private String uri;
  @Column(name = ExampleConstants.EMOJI_COLUMN_WIDTH_NAME)
  private Integer width;
}
