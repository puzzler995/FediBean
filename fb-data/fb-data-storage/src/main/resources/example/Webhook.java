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
@Table(name = ExampleConstants.WEBHOOK_TABLE_NAME)
public class Webhook implements Serializable {
  private static final long serialVersionUID = 7238574222920988883L;

  @Id
  @Column(name = ExampleConstants.WEBHOOK_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.WEBHOOK_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @Column(name = ExampleConstants.WEBHOOK_COLUMN_NAME_NAME, nullable = false, length = 128)
  private String name;

  @Column(name = ExampleConstants.WEBHOOK_COLUMN_ON_NAME, nullable = false)
  private List<String> on;

  @Column(name = ExampleConstants.WEBHOOK_COLUMN_URL_NAME, nullable = false, length = 1024)
  private String url;

  @Column(name = ExampleConstants.WEBHOOK_COLUMN_SECRET_NAME, nullable = false, length = 1024)
  private String secret;

  @Column(name = ExampleConstants.WEBHOOK_COLUMN_ACTIVE_NAME, nullable = false)
  private Boolean active = false;

  @Column(name = ExampleConstants.WEBHOOK_COLUMN_LATESTSENTAT_NAME)
  private OffsetDateTime latestSentAt;

  @Column(name = ExampleConstants.WEBHOOK_COLUMN_LATESTSTATUS_NAME)
  private Integer latestStatus;
}
