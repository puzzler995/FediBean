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
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

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
@Table(name = ExampleConstants.DRIVEFILE_TABLE_NAME)
public class DriveFile implements Serializable {
  private static final long serialVersionUID = -281458349907430603L;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_ACCESSKEY_NAME, length = 256)
  private String accessKey;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_BLURHASH_NAME, length = 128)
  private String blurhash;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_COMMENT_NAME, length = 8192)
  private String comment;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"folderId\"")
  private DriveFolder folder;
  @Id
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_ISLINK_NAME, nullable = false)
  private Boolean isLink = false;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_ISSENSITIVE_NAME, nullable = false)
  private Boolean isSensitive = false;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_MAYBEPORN_NAME, nullable = false)
  private Boolean maybePorn = false;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_MAYBESENSITIVE_NAME, nullable = false)
  private Boolean maybeSensitive = false;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_MD5_NAME, nullable = false, length = 32)
  private String md5;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_NAME_NAME, nullable = false, length = 256)
  private String name;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_PROPERTIES_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> properties;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_REQUESTHEADERS_NAME)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> requestHeaders;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_REQUESTIP_NAME, length = 128)
  private String requestIp;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_SIZE_NAME, nullable = false)
  private Integer size;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_SRC_NAME, length = 512)
  private String src;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_STOREDINTERNAL_NAME, nullable = false)
  private Boolean storedInternal = false;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_THUMBNAILACCESSKEY_NAME, length = 256)
  private String thumbnailAccessKey;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_THUMBNAILURL_NAME, length = 512)
  private String thumbnailUrl;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_TYPE_NAME, nullable = false, length = 128)
  private String type;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_URI_NAME, length = 512)
  private String uri;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_URL_NAME, nullable = false, length = 512)
  private String url;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"userId\"")
  private User user;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_USERHOST_NAME, length = 128)
  private String userHost;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_WEBPUBLICACCESSKEY_NAME, length = 256)
  private String webpublicAccessKey;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_WEBPUBLICTYPE_NAME, length = 128)
  private String webpublicType;
  @Column(name = ExampleConstants.DRIVEFILE_COLUMN_WEBPUBLICURL_NAME, length = 512)
  private String webpublicUrl;
}
