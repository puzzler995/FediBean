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
@Table(name = ExampleConstants.AD_TABLE_NAME)
public class Ad implements Serializable {
  private static final long serialVersionUID = -2627502054170805704L;

  @Id
  @Column(name = ExampleConstants.AD_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.AD_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = ExampleConstants.AD_COLUMN_EXPIRESAT_NAME, nullable = false)
  private OffsetDateTime expiresAt;

  @Column(name = ExampleConstants.AD_COLUMN_PLACE_NAME, nullable = false, length = 32)
  private String place;

  @Column(name = ExampleConstants.AD_COLUMN_PRIORITY_NAME, nullable = false, length = 32)
  private String priority;

  @Column(name = ExampleConstants.AD_COLUMN_URL_NAME, nullable = false, length = 1024)
  private String url;

  @Column(name = ExampleConstants.AD_COLUMN_IMAGEURL_NAME, nullable = false, length = 1024)
  private String imageUrl;

  @Column(name = ExampleConstants.AD_COLUMN_MEMO_NAME, nullable = false, length = 8192)
  private String memo;

  @Column(name = ExampleConstants.AD_COLUMN_RATIO_NAME, nullable = false)
  private Integer ratio;
}
