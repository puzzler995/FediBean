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
@Table(name = ExampleConstants.SIGNIN_TABLE_NAME)
public class Signin implements Serializable {
  private static final long serialVersionUID = -6048714047459501464L;
  @Column(name = ExampleConstants.SIGNIN_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
  @Column(name = ExampleConstants.SIGNIN_COLUMN_HEADERS_NAME, nullable = false)
  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> headers;
  @Id
  @Column(name = ExampleConstants.SIGNIN_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;
  @Column(name = ExampleConstants.SIGNIN_COLUMN_IP_NAME, nullable = false, length = 128)
  private String ip;
  @Column(name = ExampleConstants.SIGNIN_COLUMN_SUCCESS_NAME, nullable = false)
  private Boolean success = false;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "\"userId\"", nullable = false)
  private User user;
}
