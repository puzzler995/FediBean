package example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = ExampleConstants.GALLERYPOST_TABLE_NAME)
public class GalleryPost implements Serializable {
  private static final long serialVersionUID = -6904945485249549946L;

  @Id
  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_UPDATEDAT_NAME, nullable = false)
  private OffsetDateTime updatedAt;

  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_TITLE_NAME, nullable = false, length = 256)
  private String title;

  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_DESCRIPTION_NAME, length = 2048)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_FILEIDS_NAME, nullable = false)
  private List<String> fileIds;

  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_ISSENSITIVE_NAME, nullable = false)
  private Boolean isSensitive = false;

  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_LIKEDCOUNT_NAME, nullable = false)
  private Integer likedCount;

  @Column(name = ExampleConstants.GALLERYPOST_COLUMN_TAGS_NAME, nullable = false)
  private List<String> tags;
}
