package example;

import com.vaadin.open.App;
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
@Table(name = ExampleConstants.ACCESSTOKEN_TABLE_NAME)
public class AccessToken implements Serializable {
  private static final long serialVersionUID = 982339170950616215L;
  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"appId\"")
  private App app;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_DESCRIPTION_NAME, length = 512)
  private String description;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_FETCHED_NAME, nullable = false)
  private Boolean fetched = false;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_HASH_NAME, nullable = false, length = 128)
  private String hash;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_ICONURL_NAME, length = 512)
  private String iconUrl;
  @Id
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_LASTUSEDAT_NAME)
  private OffsetDateTime lastUsedAt;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_NAME_NAME, length = 128)
  private String name;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_PERMISSION_NAME, nullable = false)
  private List<String> permission;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_SESSION_NAME, length = 128)
  private String session;
  @Column(name = ExampleConstants.ACCESSTOKEN_COLUMN_TOKEN_NAME, nullable = false, length = 128)
  private String token;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;
}
