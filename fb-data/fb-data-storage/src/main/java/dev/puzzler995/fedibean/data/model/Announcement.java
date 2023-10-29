package dev.puzzler995.fedibean.data.model;

import dev.puzzler995.fedibean.data.model.constant.DBConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.net.URL;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DBConstant.ANNOUNCEMENT_TABLE_NAME)
public class Announcement extends DBItem {
  private static final long serialVersionUID = 4890606945895706082L;

  @Lob
  @Column(name = DBConstant.ANNOUNCEMENT_COLUMN_TEXT_NAME, nullable = false)
  private String text;

  @Lob
  @Column(name = DBConstant.ANNOUNCEMENT_COLUMN_TITLE_NAME, nullable = false)
  private String title;

  @Column(name = DBConstant.ANNOUNCEMENT_COLUMN_IMAGEURL_NAME, length = 1024)
  private URL imageUrl;

  @Column(name = DBConstant.ANNOUNCEMENT_COLUMN_UPDATEDAT_NAME)
  private OffsetDateTime updatedAt;

  @Column(name = DBConstant.ANNOUNCEMENT_COLUMN_SHOWPOPUP_NAME, nullable = false)
  private Boolean showPopup = false;
}
