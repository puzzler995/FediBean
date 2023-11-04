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
@Table(name = ExampleConstants.SWSUBSCRIPTION_TABLE_NAME)
public class SwSubscription implements Serializable {
  private static final long serialVersionUID = 5382449060641071169L;

  @Id
  @Column(name = ExampleConstants.SWSUBSCRIPTION_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;

  @Column(name = ExampleConstants.SWSUBSCRIPTION_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;

  @Column(
      name = ExampleConstants.SWSUBSCRIPTION_COLUMN_ENDPOINT_NAME,
      nullable = false,
      length = 512)
  private String endpoint;

  @Column(name = ExampleConstants.SWSUBSCRIPTION_COLUMN_AUTH_NAME, nullable = false, length = 256)
  private String auth;

  @Column(
      name = ExampleConstants.SWSUBSCRIPTION_COLUMN_PUBLICKEY_NAME,
      nullable = false,
      length = 128)
  private String publickey;

  @Column(name = ExampleConstants.SWSUBSCRIPTION_COLUMN_SENDREADMESSAGE_NAME, nullable = false)
  private Boolean sendReadMessage = false;
}
