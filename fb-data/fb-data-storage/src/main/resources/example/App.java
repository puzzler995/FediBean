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
@Table(name = ExampleConstants.APP_TABLE_NAME)
public class App implements Serializable {
  private static final long serialVersionUID = -516141725011175460L;

  @Id
  @Column(name = ExampleConstants.APP_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.APP_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "\"userId\"")
  private User user;

  @Column(name = ExampleConstants.APP_COLUMN_SECRET_NAME, nullable = false, length = 64)
  private String secret;

  @Column(name = ExampleConstants.APP_COLUMN_NAME_NAME, nullable = false, length = 128)
  private String name;

  @Column(name = ExampleConstants.APP_COLUMN_DESCRIPTION_NAME, nullable = false, length = 512)
  private String description;

  @Column(name = ExampleConstants.APP_COLUMN_PERMISSION_NAME, nullable = false)
  private List<String> permission;

  @Column(name = ExampleConstants.APP_COLUMN_CALLBACKURL_NAME, length = 512)
  private String callbackUrl;
}
