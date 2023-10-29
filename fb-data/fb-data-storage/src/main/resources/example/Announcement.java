package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Table(name = ExampleConstants.ANNOUNCEMENT_TABLE_NAME)
public class Announcement implements Serializable {
  private static final long serialVersionUID = 4639670123358875680L;

  @Id
  @Column(name = ExampleConstants.ANNOUNCEMENT_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.ANNOUNCEMENT_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = ExampleConstants.ANNOUNCEMENT_COLUMN_TEXT_NAME, nullable = false, length = 8192)
  private String text;

  @Column(name = ExampleConstants.ANNOUNCEMENT_COLUMN_TITLE_NAME, nullable = false, length = 256)
  private String title;

  @Column(name = ExampleConstants.ANNOUNCEMENT_COLUMN_IMAGEURL_NAME, length = 1024)
  private String imageUrl;

  @Column(name = ExampleConstants.ANNOUNCEMENT_COLUMN_UPDATEDAT_NAME)
  private OffsetDateTime updatedAt;

  @Column(name = ExampleConstants.ANNOUNCEMENT_COLUMN_SHOWPOPUP_NAME, nullable = false)
  private Boolean showPopup = false;

  @Column(name = ExampleConstants.ANNOUNCEMENT_COLUMN_ISGOODNEWS_NAME, nullable = false)
  private Boolean isGoodNews = false;
}
