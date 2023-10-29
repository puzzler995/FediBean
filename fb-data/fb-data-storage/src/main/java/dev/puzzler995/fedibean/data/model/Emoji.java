package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.net.URI;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DBConstant.EMOJI_TABLE_NAME)
public class Emoji extends DBItem {
  private static final long serialVersionUID = 3238682328745351861L;

  @Column(name = DBConstant.EMOJI_COLUMN_UPDATEDAT_NAME)
  private OffsetDateTime updatedAt;

  @Column(name = DBConstant.EMOJI_COLUMN_NAME_NAME, nullable = false)
  private String name;

  @Column(name = DBConstant.EMOJI_COLUMN_HOST_NAME)
  private String host;

  @Column(name = DBConstant.EMOJI_COLUMN_ORIGINALURL_NAME, nullable = false, length = 512)
  private URL originalUrl;

  @Column(name = DBConstant.EMOJI_COLUMN_URI_NAME, length = 512)
  private URI uri;

  @Column(name = DBConstant.EMOJI_COLUMN_TYPE_NAME)
  private String type;

  @ElementCollection
  @Column(name = DBConstant.EMOJI_COLUMN_TAGS_NAME)
  @CollectionTable(name = "emoji_tags", joinColumns = @JoinColumn(name = "owner_id"))
  private List<String> tags = new ArrayList<>();

  @Column(name = DBConstant.EMOJI_COLUMN_CATEGORY_NAME)
  private String category;

  @Column(name = DBConstant.EMOJI_COLUMN_PUBLICURL_NAME, nullable = false, length = 512)
  private URL publicUrl;

  @Lob
  @Column(name = DBConstant.EMOJI_COLUMN_LICENSE_NAME)
  private String license;

  @Column(name = DBConstant.EMOJI_COLUMN_WIDTH_NAME)
  private Integer width;

  @Column(name = DBConstant.EMOJI_COLUMN_HEIGHT_NAME)
  private Integer height;
}
