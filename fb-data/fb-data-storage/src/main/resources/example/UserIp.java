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
@Table(name = ExampleConstants.USERIP_TABLE_NAME)
public class UserIp implements Serializable {
  private static final long serialVersionUID = -595308023753718512L;

  @Id
  @Column(name = ExampleConstants.USERIP_COLUMN_ID_NAME, nullable = false)
  private Integer id;

  @Column(name = ExampleConstants.USERIP_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;

  @Column(name = ExampleConstants.USERIP_COLUMN_USERID_NAME, nullable = false, length = 32)
  private String userId;

  @Column(name = ExampleConstants.USERIP_COLUMN_IP_NAME, nullable = false, length = 128)
  private String ip;
}
