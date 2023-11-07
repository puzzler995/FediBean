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
@Table(name = ExampleConstants.USERPENDING_TABLE_NAME)
public class UserPending implements Serializable {
  private static final long serialVersionUID = 2775277514201889290L;
  @Column(name = ExampleConstants.USERPENDING_COLUMN_CODE_NAME, nullable = false, length = 128)
  private String code;
  @Column(name = ExampleConstants.USERPENDING_COLUMN_CREATEDAT_NAME, nullable = false)
  private OffsetDateTime createdAt;
  @Column(name = ExampleConstants.USERPENDING_COLUMN_EMAIL_NAME, nullable = false, length = 128)
  private String email;
  @Id
  @Column(name = ExampleConstants.USERPENDING_COLUMN_ID_NAME, nullable = false, length = 32)
  private String id;
  @Column(name = ExampleConstants.USERPENDING_COLUMN_PASSWORD_NAME, nullable = false, length = 128)
  private String password;
  @Column(name = ExampleConstants.USERPENDING_COLUMN_USERNAME_NAME, nullable = false, length = 128)
  private String username;
}
